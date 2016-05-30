/**
 * Created by ivanybma on 5/2/16.
 */
function menu(id,name, price, calories, pretime, category) {

        this.id=id;
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.prepTime = pretime;
        this.category = category;
}


function userorder(itemList, requiredPickupDate, requiredPickupTime, totalPrice) {

    this.itemList = itemList;
    this.totalPrepTime=0;
    this.status="";
    this.requiredPickupDate = requiredPickupDate;
    this.requiredPickupTime = requiredPickupTime;
    this.totalPrice = totalPrice;
}

function item(menu, quantity){
    this.menu = menu;
    this.quantity = quantity;
}

function sbitem(name, qty, price){
    this.name=name;
    this.qty=qty;
    this.price=price;
}