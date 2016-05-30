<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 4/27/16
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id ="basicModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style=" border:1px solid #f9d6dc; background: #fbe6ea;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <!-- popup title -->
                <h4 class="modal-title" style="color: #8f142c;">Order confirmation: </h4>
            </div>

            <!-- popup body -->
            <div id = "mlayout" class="modal-body">
                <table id="mlayout_order" class="table table-hover" style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th class="col-lg-1">Item#</th>
                        <th class="col-lg-5">Name</th>
                        <th class="col-lg-1">Qty</th>
                        <th class="col-lg-2">Price</th>
                    </tr>
                    </thead>
                    <tbody id="mlayout_bd">
                    </tbody>
                </table>
                <div>
                    <table>
                        <tr>
                            <th class="col-lg-8"></th>
                            <th class="col-lg-3">Total $</th>
                            <th class="col-lg-1" id ="mlayout_total">0.0</th>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class=" col-lg-10"><p id="mlayout_msg" align="left"></p></div>
                    <div class=" col-lg-2"><button id ="mlayout_bt" type="button" class="btn btn-default" data-dismiss="modal">Confirm</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->