## Scrum Session 1 Daily Scrum 09/29/2020

Need to setup sprint review meeting with PO soon - Ask PO if Thursday 10/01/2020 at 12:30pm will work

Questions for PO
<br/><br/>
If we are using another persons code for setting up the server/client what should we do to make sure they are given credit to avoid plagiarism?
Ask about getting shared machine for running the server/database in the CS department?

Talked about everyone running the spring react tutorial and learn how that functions so we can get the project fully started.

## Scrum Session 2  Sprint Review 10/01/2020

Should we setup another meeting with her for the planning phase?
<br/><br/>
Not until we create all tasks and acceptance criteria for higher priority user stories, while creating these tasks any questions we run into make sure to make note of them for the planning meeting.

Sprint review should happen before the presentation for her to clear up issues with the presentation. Then with this feedback make changes to the presentation, and use this for the retrospective.

Development manual is where we give credit to any outside sources of code that we use. We are allowed to use what we found with spring/react, can't use outside logic code for implementing the chessgame for example.

Does project need to be live and deployed in a public environment? 
<br/><br/>
No she will need access to it for grading, she will let us know how she would like this done.

She is asking about getting a shared machine to use for setting up the server, she will get back to us on this.

Next Tuesday 10/06 we will create tasks and acceptance criteria for the high priority user stories, come up with questions for the PO, and schedule the planning meeting with the PO.

## Scrum Session 3  Daily Scrum 10/06/2020
Created acceptance criteria document 
Created acceptance criteria for all user stories, included the additional features
Set up meeting for tomorrow to take these and create them into tasks


## Scrum Session 4  Daily Scrum 10/07/2020
Started creating tasks based off the acceptance criteria made on 10/06. 
Assigned any remaining acceptance criteria without tasks to finish before meeting on 10/08.

## Scrum Session 5  Daily Scrum 10/08/2020
Finalized acceptance criteria and tasks
Created P2 milestone set all tasks to fall under that milestone

Questions for instructor
<br/><br/>
Do we need to create user stories/acceptance criteria/tasks for the additional features that were added in P2?
Do we need to create tasks for everything? Or just what we think we might get to in Sprint 2?
What are the expectations of where the project should be at the end of this Sprint?
What should we focus on first task wise?
How other teams are deploying the server? Running on a local machine vs a shared network machine
Check new user stories/acceptance criteria that were new to Sprint 2

Clarify invite system whether an invite to a game can only be accepted by one person, after that person accepts the invite then it goes away for everyone else
vs
Invite is sent to multiple people, any number of people can accept it, each accepted invite will start a new game


## Scrum Session 6  Planning Session 10/09/2020
Met with PO to go through acceptance criteria that we have created
Need to add more acceptance criteria for corner cases of using the system, such as logging in with an invalid username/password
Need to rework class diagram, some of the relationship lines are pointing in the wrong direction
Discussed expectations of P2- Should be around 40% done with the user stories 
Discussed getting server/database system from CSU so we can shared that- need to email her to ask her for this

## Scrum Session 7  Daily Scrum 10/13/2020
Ask PO to clarify -- Tasks for the user stories (or at least for the ones with the highest priority) are missing. in P1 grading<br/><br/>
Fixed up UML diagram<br/><br/>
Fixed up Scrum Session document formatting<br/><br/>
Added Inbox/Notification system epic so all invite/notification tasks fall under this<br/><br/>

## Scrum Session 8  Daily Scrum 10/15/2020
Agreed upon Jared's branch as the starting point for the project<br/><br/>
Got everyone to build and run this branch<br/><br/>
Discussed what tasks to begin on<br/><br/>
Merged this branch and got started<br/><br/>

## Scrum Session 9  Daily Scrum 10/20/2020
Added node_modules folder to gitignore <br/><br/>
Conor and Jon showed their branches to the rest of the group and how they functione<br/><br/>
Merged both of these branches, system can now create an account and login to this account<br/><br/>
Closed issues 8, 9, 3, 1, 49, 11

## Scrum Session 10  Daily Scrum 10/22/2020
Closed issue #12<br/><br/>
Discussed planning of the messaging system so users can send out invites<br/><br/>
Need to create a game and notification table in the database<br/><br/>
Need to create Traceability link matrix document<br/><br/>
Closed epic #3<br/><br/>

## Scrum Session 11  Daily Scrum 10/27/2020
Decided P2 stopping after game invites can be sent further tasks completed will be in P3<br/><br/>
Set up meeting time at 12:00-2:00 on Friday with PO, backup meeting time Thursday 10:00am<br/><br/>
Start making slides for presentation<br/><br/>
Closed issues #50, 51, 20, 77, 22, 19<br/><br/>
Closed epic #5<br/><br/>

## Scrum Session 12  Daily Scrum 10/29/2020
Jared brought up issues with sending out game invites, discussed possible solutions
Cory brought up issues with displaying account information, state was loading the previous account not the current one, discussed possible solutions
Mike showed off the Chessboard he has made, looks very nice
Conor/Jon created the wiki page and updated all of the files that are a part of this page with everything for P2

Sprint Review meeting setup for Friday 10/30/20 at 12:00pm
Problems <br/><br/>
Some tasks not thought out fully, some overlapped with others. So when a pull request was put out it would sometimes override another. Then later on the task would still be in the backlog, and we would have to discuss whether it had been finished or not.<br/><br/>
Building the project from the start was difficult had to add correct folders/files into gitignore in order to get rid of issues.<br/><br/> 

What went well<br/><br/>
Once everything was in place we all jumped into tasks and started working efficiently. Got a lot done in a short amount of time.<br/><br/> 
Good Zenhub etiquette, everyone followed rules on taking a task putting it into "In Progress". Then when finished would make pull requests and ask people to test it and review it before having someone merge it.<br/><br/>

## Scrum Session 13 Sprint Review 10/30/2020
Conor and Jon demonstrated the current state of the application to the Product Owner. Conor showed off the functionality of the sign up page and logged in to the application. Jon showed off the dashboard, inbox, the ability to search for users, and the stats pages for multiple users.

Feedback from the Product Owner<br/><br/>
The password should be hidden when signing up and logging in.<br/> 
After signing out, a user should not be able to be able to access pages within the app by hitting the back button in their browser.<br/>
The inheritance arrows in the class diagram need to be reversed.<br/>
Include JavaScript classes in class diagram with a distinction between packages.<br/>
Include all implemented Java classes in class diagram.<br/>
Reconsider whether or not the Invite class and Notification class should have an is-a relationship.<br/>
Reconsider whether or not the Match class and Game class should have an is-a relationship.<br/>


