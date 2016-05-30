<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 4/27/16
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id ="OrderDetailModal" class="modal fade" tabindex="-1" role="dialog" style="z-index: 2000;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style=" border:1px solid #f9d6dc; background: #fbe6ea;">
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <!-- popup title -->
                <h4 class="modal-title" style="color: #8f142c;">Order Detail: </h4>
            </div>

            <!-- popup body -->
            <div id = "orderdetaillayout" class="modal-body">
                <h5 class="text-center">Total price: <a id="ttprice"></a></h5>
                <table id="orderlayout_order" class="table table-hover" style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th class="col-lg-5">Name</th>
                        <th class="col-lg-1">Qty</th>
                        <th class="col-lg-2">Price</th>
                    </tr>
                    </thead>
                    <tbody id="orderlayout_bd">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->