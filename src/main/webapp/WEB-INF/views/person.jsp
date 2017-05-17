<%-- 
    Document   : person.jsp
    Created on : Aug 13, 2016, 8:04:03 PM
    Author     : Hardik
--%>
<center>
    <div class="generic-container" >
        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">Person Form</span></div>
            <div class="formcontainer">
                <form name="myForm" id="myForm1" ng-submit="submit()" class="form-horizontal" >

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="firstname">First Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="person.firstname" name="firstname" id="firstname" class="username form-control input-sm" placeholder="Enter your First name" required />
                                <p ng-show="myForm.firstname.$error.required" class="help-block">First Name is required.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="lastname">Last Name</label>
                            <div class="col-md-7">
                                <input type="text"  id="lastname" name="lastname" ng-model="person.lastname" class="username form-control input-sm" placeholder="Enter your Last name" required/>
                                <p ng-show="myForm.lastname.$error.required" class="help-block">Last Name is required.</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="birthdate">Birthdate</label>
                            <div class="col-md-7">
                                <input type="date" datetime="yyyy-MM-dd" name="birthdate"  id="birthdate" ng-model="person.birthdate" class="username form-control input-sm" placeholder="Enter your Birthdate" required/>
                                <p ng-show="myForm.lastname.$error.required" class="help-block">Birthdate is required.</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="description">Description</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="person.description"  id="description" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="amount">Amount</label>
                            <div class="col-md-7">
                                <input type="number"  id="amount" ng-model="person.amount" class="email form-control input-sm" />
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-actions floatcenter">
                            <input type="submit"  value="Update" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                            &nbsp;
                            <input type="submit"  value="Create" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        </div>
                    </div>
                </form>
            </div>
        </div>    
        <div class="panel panel-default" >
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Persons </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>FullName</th>
                            <th>BirtDate</th>
                            <th>Amount</th>
                            <th>SFID</th>
                            <th width="20%">Action</th>
                        </tr>
                    </thead>
                    <tbody style="text-align:left;">
                        <tr ng-repeat="u in persons">
                            <td><span ng-bind="u.id"></span></td>
                            <td><span ng-bind="u.autoNumberName"></span></td>
                            <td><span ng-bind="u.firstname"></span></td>
                            <td><span ng-bind="u.lastname"></span></td>
                            <td><span ng-bind="u.fullname"></span></td>
                            <td><span ng-bind="u.birthdate"></span></td>
                            <td><span ng-bind="u.amount"></span></td>
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
