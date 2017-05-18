<%-- 
    Document   : person.jsp
    Created on : Aug 13, 2016, 8:04:03 PM
    Author     : Hardik
--%>
<center>
    <div class="generic-container" >
        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">Address Form</span></div>
            <div class="formcontainer">
                <form name="addressForm" id="addressForm1" ng-submit="submit()" class="form-horizontal" >

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="statename">State Name</label>
                            <div class="col-md-7">
                                <select ng-model="address.statename" name="statename" id="statename" required>
                                    <option value="">None</option>
                                    <option value="New York">New York</option>
                                    <option value="California">California</option>
                                    <option value="Illinois">Illinois</option>
                                </select>

                                <p ng-show="addressForm.statename.$error.required" class="help-block">State Name is required.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="addressline1">Address Line1</label>
                            <div class="col-md-7">
                                <input type="text"  id="addressline1" name="addressline1" ng-model="address.addressline1" class="username form-control input-sm" placeholder="Enter your Address Line1" required/>
                                <p ng-show="addressForm.addressline1.$error.required" class="help-block">Address Line1 is required.</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="addressline2">Address Line2</label>
                            <div class="col-md-7">
                                <input type="test" name="addressline2"  id="addressline2" ng-model="address.addressline2" class="username form-control input-sm" placeholder="Enter your Address Line2" required/>
                                <p ng-show="addressForm.addressline2.$error.required" class="help-block">Address Line2 is required.</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="zipCode">Zip Code</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="address.zipCode" name="zipCode"  id="zipCode" class="username form-control input-sm" placeholder="Enter your ZipCode" required/>
                                <p ng-show="addressForm.zipCode.$error.required" class="help-block">ZipCode is required.</p>
                            </div>
                        </div>
                    </div>



                    <div class="row">
                        <div class="form-actions floatcenter">
                            <input type="submit"  value="Update" class="btn btn-primary btn-sm" ng-disabled="addressForm.$invalid">
                        </div>
                    </div>
                </form>
            </div>
        </div>    
        <div class="panel panel-default" >
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Address </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>StateName</th>
                            <th>AddressLine 1</th>
                            <th>AddressLine 2</th>
                            <th>ZipCode</th>
                            <th>SFID</th>
                            <th width="20%">Action</th>
                        </tr>
                    </thead>
                    <tbody style="text-align:left;">
                        <tr ng-repeat="u in addresses">
                            <td><span ng-bind="u.id"></span></td>
                            <td><span ng-bind="u.autoNumberName"></span></td>
                            <td><span ng-bind="u.statename"></span></td>
                            <td><span ng-bind="u.addressline1"></span></td>
                            <td><span ng-bind="u.addressline2"></span></td>
                            <td><span ng-bind="u.zipCode"></span></td>
                            <td><span ng-bind="u.sfId"></span></td>
                            <td>
                                <button type="button" ng-click="edit(u.id)" class="btn btn-success custom-width">Edit</button>  
                                &nbsp;
                                <button type="button" ng-click="delete(u.id)" class="btn btn-warning custom-width">Delete</button>  
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</center>       
