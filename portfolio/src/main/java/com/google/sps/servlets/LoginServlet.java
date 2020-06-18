// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.sps.data.Login;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Authentication Servlet 

Modify your DataServlet.java file to get the current user's email address, and store it 
alongside the text of the comment.

Add the user's email address to the JSON returned by your comments servlet.

Test that this works by running a dev server and navigating to the comments servlet's URL. 
You should see the JSON in the browser.

When you get this step working, create a pull request and send it to your advisor for review!

Modify your JavaScript to show the email address of the user who posted each comment.

When you get this step working, create a pull request and send it to your advisor for review!

(Optional) Add support for users specifying display names, 
so you no longer need to show email addresses.



*/
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    UserService userService = UserServiceFactory.getUserService();
    String status = "";
    if (userService.isUserLoggedIn()){
       status = "yes";
    }
    String loginUrl = userService.createLoginURL("/index.html");
    String logoutUrl = userService.createLogoutURL("/index.html");
    Login login = new Login(status, loginUrl, logoutUrl);
    Gson gson = new Gson();
    response.getWriter().println(gson.toJson(login));
  }

}
