<%-- 
    Document   : person.jsp
    Created on : Aug 13, 2016, 8:04:03 PM
    Author     : Hardik
--%>
<center>
    <div class="generic-container" >
        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">Phone Form</span></div>
            <div class="formcontainer">
                <form name="phoneForm" id="phoneForm" ng-submit="submit()" class="form-horizontal" >

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="type">Type</label>
                            <div class="col-md-7">
                                <select ng-model="phone.type" name="type" id="type" required>
                                    <option value="">None</option>
                                    <option value="Home">Home</option>
                                    <option value="Mobile">Mobile</option>
                                    <option value="Office">Office</option>
                                </select>

                                <p ng-show="phoneForm.type.$error.required" class="help-block">Type is required.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="phone">Phone</label>
                            <div class="col-md-7">
                                <input type="text"  id="phone" name="phone" ng-model="phone.phone" class="username form-control input-sm" placeholder="Enter your Phone No." required/>
                                <p ng-show="phoneForm.phone.$error.required" class="help-block">Phone is required.</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatcenter">
                            <input type="submit"  value="Update" class="btn btn-primary btn-sm" ng-disabled="phoneForm.$invalid">
                        </div>
                    </div>
                </form>

            </div>
        </div>    
        <div class="panel panel-default" >
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Phone </span></div>
            <div class="tablecontainer">
                <table class="table table-hover table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Phone</th>
                            <th>SfId</th>
                            <th width="20%">Action</th>

                        </tr>
                    </thead>
                    <tbody style="text-align:left;">
                        <tr ng-repeat="u in phones">
                            <td><span ng-bind="u.id"></span></td>
                            <td><span ng-bind="u.autoNumberName"></span></td>
                            <td><span ng-bind="u.type"></span></td>
                            <td><span ng-bind="u.phone"></span></td>
                            <td><span ng-bind="u.sfId"></span></td>
                            <td>
                                <button type="button" ng-click="edit(u.id)" class="btn btn-success custom-width">Edit</button>  
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</center>       
