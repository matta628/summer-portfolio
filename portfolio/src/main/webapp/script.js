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

/**
 * Adds a random Parks & Rec quote to the page.
 */
function addRandomQuote() {
  const quotes =
      ['"I once worked with a guy for three years and never learned his name. Best friend I ever had. We still never talk sometimes." -Ron Swanson', 
      '\"Everything hurts and I\'m dying.\" -Leslie Knope', 
      '"No matter what I do, literally nothing bad can happen to me. I\'m like a white, male US Senator." -Leslie Knope', 
      '"Treat yo self." -Donna Meagle & Tom Haverford',
      '“If I had to have a stripper’s name, it would be equality.” -Leslie Knope',
      '“If there were more food and fewer people, this would be a perfect party.” -Ron Swanson',
      '“The only things I like are dogs, sleeping late and weird birthmarks.” -April Ludgate',
      '“Leslie, I typed your symptoms into the thing up here, and it says you could have network connectivity problems.” -Andy Dwyer',
      '“My marbles are full of mouth today.” -Jerry (aka Gary aka Larry) Gergich',
      '“Calc-you-later.” -Ben Wyatt',
      '“I am 100% certain that I am 0% sure of what I’m going to do.” -Chris Trager',
      '“I’m putting myself out there, meeting new people, having some casual fun and it is...awkward.” -Ann Perkins'];

  // Pick a random greeting.
  const quote = quotes[Math.floor(Math.random() * quotes.length)];

  // Add it to the page.
  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerText = quote;
}

/** Adds messages (data) to website */
function addData(){
    var loggedIn = false;
    fetch("/login")
        .then(response => response.json())
        .then( (authentication) => {
            loggedIn = authentication.status;
            if (loggedIn){
                fetch("/data")
                    .then(response => response.json())
                    .then((comments) =>{
                        const dataContainer = document.getElementById("data-container");
                        dataContainer.innerHTML = '';
                        comments.forEach( comment => {
                            dataContainer.appendChild(createListElement(comment));  
                        });
                    });
                document.getElementById("login").innerHTML = "<p>Logout <a href=\"" + authentication.logout + "\">here</a>.</p>";
            }
            else{
                document.getElementById("form").style.display="none";
                document.getElementById("login").innerHTML = "<p>Login to view comments <a href=\"" + authentication.login + "\">here</a>.</p>";
            }
        });


}

/** Creates an <li> element containing text,  */
function createListElement(comment) {
  const liElement = document.createElement('li');
  liElement.innerText = "<" + comment.email + ">: " + comment.text;
  return liElement;
}