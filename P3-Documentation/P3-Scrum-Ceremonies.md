## Scrum Session 1 Daily Scrum 11/03/2020

Created P3 milestone<br/>
Moved remaining tasks to fall under P3<br/>
Discussed what to start working on
- Game invites being sent out
- Rework Java Controller/Service/DAO classes to split up functionality 

## Scrum Session 2 Daily Scrum 11/05/2020
Merged Jons' pull request for client sending invitation requests to server closed issue #34<br/>
Conor showed a demo sending invites to players and having it show up in their invitation list<br/>
- Need to work on players not being able to invite themselves
- Need a confirmation that the invite(s) were sent and then redirect back to dashboard
- Need to add a status to matches table to show whether a match has been accepted, rejected, abandoned, or pending<br/>

Planning on having Sprint Planning meeting with PO on Tuesday at 12:30pm<br/>

## Scrum Session 3 Sprint Planning 11/10/2020

Talked with PO about what to work on next <br/>
- Need to breakdown acceptance criteria more specifically for the user playing a games of chess. Example of if the user tries to make an invalid move, how does the system respond?
- Same priorties as last sprint
- Need to have all of P1 user stories done. Exceptions will be made for the Could Haves
- She brought up testing being an issue for a lot of teams, acceptance criteria should translate to tests

Corey is working on getting Invitations Accepted/Rejected<br/>
Rest of team needs to figure out the board object being passed from client/server/database will work<br/>

## Scrum Session 4 Daily Scrum 11/12/2020

Discussed possible solutions with creating and storing the ChessBoard object in the database<br/>
- Potentially have it stored as a JSON string in database, then transferring it around will be easy
- Client/Server would both need the ability to parse the JSON string into an object array

## Scrum Session 5 Daily Scrum 11/17/2020
- Showed off accepting an invite initializing chessboard object, clicking play game sends request to server to get the Chessboard state on client
- Working on server being able to pull the JSON string from database and translate that into a ChessBoard object
- Working on unregistering an account, brought up issues with deleting an account with their ID cascading with other tables. Talked about solutions
- Working on implementing Chesspieces that arent already finished, brought up issues of adding the Portal piece and how that affects other pieces
- Working on getting the client to send a move to the server and having the server process that move

## Scrum Session 6 Daily Scrum 11/19/2020
- Finished having the server pull JSON board string and translate that into a ChessBoard object
- Finished unregistering an account
- Started working on adding the Portal chesspiece and the interactions with other chesspieces
- Still working on client sending a move to the server and handling that

## Scrum Session 7 Daily Scrum 11/24/2020
- Created a refresh lists button on Dashboard that refreshes the Matches and Invitations List
- Added a timestamp for both start and end times of a Match
- Client can now send a move request to server and have it process that correctly
- Portal piece has now been added as a possibility in the Chessboard
- Working on implementing turns
- Working on having the first turn be placing the Portal piece

## Scrum Session 8 Daily Scrum 12/01/2020
- Added ablity to tell who's turn it is on the Game screen
- Whoever is waiting for the other player to make a move, their client will ask if any changes have occurred every 3 seconds
- Added Portal piece to board as well as black hole functionality 
- Imeplemented turns
- Added pawn promotion

## Scrum Session 9 Daily Scrum 12/03/2020
- Added end condition for a chess match
- Fixed portals throwing invalidLocation  
- Setup Sprint Review for Friday 12/03 at 5:30pm with PO
- Discussed how pieces will interact with Portals
- Discussed what will be shown during the sprint review
- Working on adding more tests
- Working on Match History page

## Scrum Session 10 Sprint Review 12/04/2020
- A bunch of tests are now added in
- Finished match history page
- Pawn and Knight can now use portals, portals can be moved onto other pieces to teleport them
- Showed Demo to Product Owner - Got feedback planned adjustments with feedback in mind
- Setup time for 12/05 at 9:00am to create Presentation

## Scrum Session 11 Presentation Creation and Sprint Retrospective 12/05/2020
- Finalized all documentation for P3
- Created Presentation for P3 with slides, demo, and recording a video
## Sprint Retrospective<br/>
- Overall we almost got everything done that was assigned in the original documentation
- Everyone contributed a lot this final Sprint
- Good communication, everyone helped eachother out if their were issues
- Should have started implementing Portal stuff sooner, this ended up being much more work than we anticipated
