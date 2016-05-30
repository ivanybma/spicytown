package com.spicytown.controller;

import com.spicytown.component.SmtpMailSender;
import com.spicytown.model.entity.*;
import com.spicytown.service.ChefSchedule.ChefScheduleServices;
import com.spicytown.service.Menu.MenuServices;
import com.spicytown.service.User.UserServices;
import com.spicytown.service.UserOrder.UserOrderServices;
import com.spicytown.utils.calculation.TimeCalculation;
import com.spicytown.utils.date.DateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cheyikung on 5/2/16.
 */

@Controller
@RequestMapping("/order")
public class UserOrderController {


//    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");


    private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderController.class);

    // it will automatically set by scheduler if the restaurant is opened
    private static boolean isOpen = false;
    private static String todayDate;

    private DateData dateData;

    // test the order see if it is a future order
    private boolean isFutureOrder = false;
    private DateData date = new DateData();

    @Autowired
    SmtpMailSender smtpMailSender;

    @Autowired
    UserServices userServices;

    @Autowired
    ChefScheduleServices chefScheduleServices;

    @Autowired
    UserOrderServices userOrderServices;

    @Autowired
    MenuServices menuServices;


    @RequestMapping("/findOrderByUserId/{userId}")
    @ResponseBody
    public List<UserOrder> findOrderByUserId(@PathVariable("userId") long userId) {
        User user = userServices.findUserAccountById(userId);

        if (user == null) {
            return null;
        }
        return userOrderServices.findOrderByUser(user);
    }

    @RequestMapping("/findorder/{id}")
    @ResponseBody
    public String findOrderById(@PathVariable("id") long id) {
        UserOrder userOrder = userOrderServices.findOrderById(id);
        return Long.toString(userOrder.getTotalPrepTime());
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllOrder() {
        userOrderServices.deleteAllOrder();
        return null;
    }

    //TODO------- test find order
    @RequestMapping(value = "/testfindorder/{startdate}/{enddate}/{sort}")
    @ResponseBody
    public List<OrderDetail> findOrderByDatesTest(@PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate, @PathVariable("sort") String sort) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();

        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setName("Coke");
        itemDetail.setQuantity(1);
        itemDetail.setPrice(1.5d);
        orderDetail.setOrderId(1l);
        orderDetail.getItemList().add(itemDetail);
        orderDetail.setCustomerEmail("abc@gmail.com");
        orderDetail.setStatus("Confirmed");
        orderDetail.setOrderDateTime("2015/02/02 10:00AM");
        orderDetail.setFullfillmentDateTime("2015/02/02 10:00AM");
        orderDetail.setReadyDatetime("2015/02/02 10:00AM");
        orderDetail.setRequiredPickupDateTime("2015/02/02 10:00AM");
        orderDetail.setTotalPrice(1.5d);
        orderDetailList.add(orderDetail);

        return orderDetailList;
    }

    @RequestMapping(value = "/findpopularity/{startdate}/{enddate}/{category}")
    @ResponseBody
    public List<MenuPopularity> findPopularity(@PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate, @PathVariable("category") String category) {
        startdate = startdate.replace("-", "");
        enddate = enddate.replace("-", "");
        List<UserOrder> userOrderList = null;
        if (startdate.equals("_") && enddate.equals("_")) {
            userOrderList = userOrderServices.findAllOrder();
        }else if(startdate.equals("_") || enddate.equals("_")) {
            return null;
        }else {
            userOrderList = userOrderServices.findUserOrderByDates(startdate, enddate);
        }
        List<MenuPopularity> menuPopularityList = new ArrayList<>();
        for (UserOrder userOrder : userOrderList) {
            for (Item item : userOrder.getItemList()) {
                if (item.getMenu().getCategory().equals(category)) {
                    boolean isfound = false;
                    for (MenuPopularity menuPopularity : menuPopularityList) {
                        if (menuPopularity.getId() == item.getMenu().getId()) {
                            menuPopularity.setTotalOrderNum(menuPopularity.getTotalOrderNum() + item.getQuantity());
                            isfound = true;
                            break;
                        }
                    }
                    if (!isfound) {
                        MenuPopularity menuPopularity = new MenuPopularity();
                        menuPopularity.setId(item.getMenu().getId());
                        menuPopularity.setCategory(item.getMenu().getCategory());
                        menuPopularity.setName(item.getMenu().getName());
                        menuPopularity.setPrice(item.getMenu().getPrice());
                        menuPopularity.setCalories(item.getMenu().getCalories());
                        menuPopularity.setPrepTime(item.getMenu().getPrepTime());
                        menuPopularity.setFilename(item.getMenu().getFilename());
                        menuPopularity.setContentType(item.getMenu().getContentType());
                        menuPopularity.setTotalOrderNum(item.getQuantity());
                        menuPopularityList.add(menuPopularity);
                    }
                }
            }
        }

        Collections.sort(menuPopularityList, Collections.reverseOrder(new Comparator<MenuPopularity>() {
            @Override
            public int compare(MenuPopularity o1, MenuPopularity o2) {
                return o1.getTotalOrderNum() - o2.getTotalOrderNum();
            }
        }));

        return menuPopularityList;
    }


    @RequestMapping(value = "/findorder/{startdate}/{enddate}/{sort}")
    @ResponseBody
    public List<OrderDetail> findOrderByDates(@PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate, @PathVariable("sort") String sort) {
        List<UserOrder> userOrderList = null;
        if (startdate.equals("_") && enddate.equals("_")) {
            userOrderList = userOrderServices.findAllOrder();
            return convertUserOrderListToOrderDetail(userOrderList, sort);
        } else if(startdate.equals("_") || enddate.equals("_")){
            return null;
        }
        startdate = startdate.replace("-", "");
        enddate = enddate.replace("-", "");
        userOrderList = userOrderServices.findUserOrderByDates(startdate, enddate);
        if (userOrderList.size() > 0) {
            return convertUserOrderListToOrderDetail(userOrderList, sort);
        }
        return null;
    }

    private List<OrderDetail> convertUserOrderListToOrderDetail(List<UserOrder> userOrderList, String sort) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        dateData = new DateData();
        for (UserOrder userOrder : userOrderList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(userOrder.getId());
            List<ItemDetail> itemDetailList = new ArrayList<>();
            for (Item item : userOrder.getItemList()) {
                ItemDetail itemDetail = new ItemDetail();
                Menu menu = item.getMenu();
                itemDetail.setName(menu.getName());
                itemDetail.setPrice(menu.getPrice());
                itemDetail.setQuantity(item.getQuantity());
                itemDetailList.add(itemDetail);
            }
            orderDetail.setItemList(itemDetailList);
            User user = userServices.findUserAccountById(userOrder.getUser().getId());
            orderDetail.setCustomerEmail(user.getName());
            orderDetail.setStatus(userOrder.getStatus());
            stringBuilder.setLength(0);
            stringBuilder.append(dateData.dateFormatter(userOrder.getOrderDate()));
            stringBuilder.append(" ");
            stringBuilder.append(dateData.timeFormatter(userOrder.getOrderTime()));
            orderDetail.setOrderDateTime(stringBuilder.toString());
            stringBuilder.setLength(0);
            List<ChefSchedule> chefScheduleList = chefScheduleServices.getChefScheduleByUserOrder(userOrder);

            int fulfillTime = 1440;
            for (ChefSchedule chefSchedule : chefScheduleList) {
                if (chefSchedule.getStartTime() < fulfillTime) {
                    fulfillTime = chefSchedule.getStartTime();
                }
            }
            stringBuilder.append(dateData.dateFormatter(userOrder.getRequiredPickupDate()));
            stringBuilder.append(" ");
            stringBuilder.append(dateData.timeFormatter(fulfillTime));
            orderDetail.setFullfillmentDateTime(stringBuilder.toString());
            stringBuilder.setLength(0);
            stringBuilder.append(dateData.dateFormatter(userOrder.getRequiredPickupDate()));
            stringBuilder.append(" ");
            stringBuilder.append(dateData.timeFormatter(userOrder.getRequiredPickupTime()));
            orderDetail.setReadyDatetime(stringBuilder.toString());
            orderDetail.setRequiredPickupDateTime(stringBuilder.toString());
            orderDetail.setTotalPrice(userOrder.getTotalPrice());
            orderDetailList.add(orderDetail);
        }
        if (sort.equals("ordertime")) {
            Collections.sort(orderDetailList, new Comparator<OrderDetail>() {
                @Override
                public int compare(OrderDetail o1, OrderDetail o2) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
                    Date d1 = null;
                    Date d2 = null;

                    try {
                        d1 = df.parse(o1.getOrderDateTime());
                        d2 = df.parse(o2.getOrderDateTime());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return d1.compareTo(d2);
                }
            });
        } else if (sort.equals("fullfillstr")) {
            Collections.sort(orderDetailList, new Comparator<OrderDetail>() {
                @Override
                public int compare(OrderDetail o1, OrderDetail o2) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = df.parse(o1.getFullfillmentDateTime());
                        d2 = df.parse(o2.getFullfillmentDateTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return d1.compareTo(d2);
                }
            });
        }
        return orderDetailList;
    }

    //finished
    //don't delete
    @RequestMapping(value = "/cancelorder/{id}")
    @ResponseBody
    public String cancelOrderById(@PathVariable("id") long id) {
        return userOrderServices.cancelOrderById(id);
    }

    @RequestMapping(value = "/deleteorder/{id}")
    @ResponseBody
    public String deleteOrderById(@PathVariable("id") long id) {
        return userOrderServices.deleteOrderById(id);
    }

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    @ResponseBody
    // TODO need to complete addOrder function
    public String addOrder(@RequestBody UserOrder userOrder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userServices.findUserAccountByName(username);

        if (user != null) {
            userOrder.setUser(user);
        } else {
            return "please sign in to place order";
        }

        // validating user order obj
        UserOrder validOrder = validatingUserOrderObj(userOrder);

        // save user order to get user order id
        userOrderServices.saveOrder(validOrder);

        //TODO: ------to be deleted-------
        System.out.println("require pickup time: " + validOrder.getRequiredPickupTime());
        System.out.println("is open?" + isOpen);
        System.out.println("today's date: " + todayDate);

        int totalPrepTime = validOrder.getTotalPrepTime();

        TimeCalculation timeCalculation = new TimeCalculation();
        System.out.println("current time is: " + new DateData().getCurrentTime());
        timeCalculation.setTotalPrepTime(totalPrepTime);

        int startTime = timeCalculation.getStartTime();
        int pickupTime = timeCalculation.getEndTime();
        int slot = timeCalculation.getSlot();
        String errorMessage = null;

        System.out.println("startTime: " + startTime + " endTime: " + pickupTime + "slot: " + slot + "required time %30 : " + timeCalculation.getRequiredTime());
        // TODO: --------------------------

        // TODO need a loop to check if the restaurant still  take the order

        String message = validatingOrder(validOrder);

        // check schedule
        if (message == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Date:");
            sb.append(dateData.dateFormatter(validOrder.getRequiredPickupDate()));
            sb.append(" ");
            sb.append("Time:");
            sb.append(dateData.timeFormatter(validOrder.getRequiredPickupTime()));
            userOrderServices.saveOrder(validOrder);
            try {
                // send message body: Send the URL+ID (http://yourapp.com/confirm?id=0123) as an email using javamail to the user;
                smtpMailSender.send(user.getName(),
                        "Order Confirmation " + validOrder.getId(),
                        "Hello Customer "
                                + user.getName()
                                + "\r\n"
                                + "Thank you for shopping with Spicytown, your order "
                                + validOrder.getId() + " has been confirmed. Please come and pick your order on time."
                                + "\r\n"
                                + "Thank you for your business,"
                                + "\r\n"
                                + "Spicytown");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        } else {
            System.out.println(message);
            userOrderServices.deleteOrder(validOrder);
            return "-1";
        }
    }

    private UserOrder validatingUserOrderObj(UserOrder userOrder) {
        UserOrder validUserOrder = new UserOrder();
        validUserOrder.setUser(userOrder.getUser());
        validUserOrder.setOrderDate(date.getCurrentDate());
        validUserOrder.setOrderTime(date.getCurrentTime());
        for (Item item : userOrder.getItemList()) {
            Menu menu = menuServices.findMenuById(item.getMenu().getId());
            Item validItem = new Item();
            validItem.setMenu(menu);
            validItem.setQuantity(item.getQuantity());
            validItem.setUserOrder(validUserOrder);
            validUserOrder.addItem(validItem);
        }

        if (userOrder.getRequiredPickupDate().length() <= 0) {
            validUserOrder.setRequiredPickupDate(date.getCurrentDate());
        } else {
            //yyyyMMdd
            String date = userOrder.getRequiredPickupDate();
            StringBuilder sb = new StringBuilder();
            sb.append(userOrder.getRequiredPickupDate().split("/")[2]);
            sb.append(userOrder.getRequiredPickupDate().split("/")[0]);
            sb.append(userOrder.getRequiredPickupDate().split("/")[1]);
            validUserOrder.setRequiredPickupDate(sb.toString());
        }
        if (userOrder.getRequiredPickupTime() == 0) {
            validUserOrder.setRequiredPickupTime(0);
        } else {
            //convert time
            int hour = userOrder.getRequiredPickupTime() / 100;
            int min = userOrder.getRequiredPickupTime() % 100;
            String time = Integer.toString(hour) + ":" + Integer.toString(min);
            validUserOrder.setRequiredPickupTime(date.minConverter(time));
        }

        return validUserOrder;
    }


    public String validatingOrder(UserOrder userOrder) {

        int requiredTime = userOrder.getRequiredPickupTime();
        int totalPrepTime = userOrder.getTotalPrepTime();
        dateData = new DateData();
        // check if the restaurant has opened or not
        if (!isOpen) {
            return "The restaurant is closed, Our operating Hour is 6 A.M. - 9 P.M.";
        }

        // check if the total preparation time is not exist or equals to zero
        // if the value is less or equal to zero, then the order is invalid
        if (totalPrepTime <= 0) {
            return "This Order is Invalid";
        }

        if (requiredTime > 0) {
            // this test if the pick up time is less than the preparation time
            if (todayDate.compareTo(userOrder.getRequiredPickupDate()) == 0 && requiredTime < dateData.getCurrentTime() + totalPrepTime) {
                return "This order cannot be completed due to not enough time to cook";
            }

            // compare with date see if the date is history date
            if (todayDate.compareTo(userOrder.getRequiredPickupDate()) > 0) {
                return "Date Selection Error";
            } else {

                String scheduleMsg = null;
                TimeCalculation timeCalculation = new TimeCalculation();
                timeCalculation.setRequiredTime(requiredTime);
                timeCalculation.setTotalPrepTime(totalPrepTime);

                int pickupTime = timeCalculation.getEndTime();
                int startTime = timeCalculation.getStartTime();
                int slot = timeCalculation.getSlot();
                scheduleMsg = chefScheduleServices.setSchedule(startTime, slot, userOrder.getRequiredPickupDate(), userOrder);

                if (scheduleMsg != null) {
                    return "Sorry, We have full capacity of today's order, please come back later";
                }

                userOrder.setRequiredPickupTime(pickupTime);
                userOrder.setStatus("Confirmed");
            }

        } else {

//            Log.debug("The user has not predefine the pick up time for the order: ");
            // test see if the order is submitted witin non operation hours
            TimeCalculation timeCalculation = new TimeCalculation();
            timeCalculation.setTotalPrepTime(totalPrepTime);

            int startTime = timeCalculation.getStartTime();
            int pickupTime = timeCalculation.getEndTime();
            int slot = timeCalculation.getSlot();
            String scheduleMsg = null;


            // TODO need a loop to check if the restaurant still can take the order
            while (pickupTime <= 21 * 60) {
                timeCalculation.setStartTime(startTime);
                pickupTime = timeCalculation.getRequiredTime();
                scheduleMsg = chefScheduleServices.setSchedule(startTime, slot, userOrder.getRequiredPickupDate(), userOrder);
                if (scheduleMsg == null) {
                    // already insert into chefscheduler
                    break;
                }
                startTime += 30;
            }


            if (pickupTime > 21 * 60) {
                return "Sorry, We have full capacity of today's order, please come back later";
            }

            userOrder.setRequiredPickupTime(pickupTime);
            userOrder.setStatus("Confirmed");
        }

        return null;
    }

    public static void setOpen(boolean open) {
        isOpen = open;
    }

    public static boolean isIsOpen() {
        return isOpen;
    }

    public static void setTodayDate(String todayDate) {
        UserOrderController.todayDate = todayDate;
    }


}
