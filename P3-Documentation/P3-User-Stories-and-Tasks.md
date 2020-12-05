# Portal Chess User Stories

### Must have

As a user, I would like to...
* Create an account on the system by providing an email and creating a username/nickname and password {Completed}
  * #11 Handle account creation request {Completed}
  * #49 Validate account creation input {Completed}
  * #12 Send account creation request to server {Completed}
  * #8 Create homepage that has login or create account buttons {Completed}
  * #50 Create popup on client for inputting account information {Completed}
* Be able to log in once an account is created {Completed}
  * #1 Server handle login request {Completed}
  * #9 Client send login request to server {Completed}
  * #51 Client launch dashboard {Completed}
* Create a new match {Completed}
  * #19 Create a button to start a chess match in UI {Completed}
  * #22 Create an invite to match screen {Completed}
  * #18 Create a new match in the system {Completed}
* Invite friends/opponents to the match {Completed}
  * #20 Seach users {Completed}
  * #77 Populate invitation list on the dashboard {Completed}
  * #52 Create inbox button {Completed}
  * #34 Send game invites {Completed}
  * #23 Server handle game invite request {Completed}
* Start the game when enough people have joined {Completed}
  * #65 Create board {Completed}
  * #60 Initialize chess board {Completed}
  * #59 Create queen piece {Completed}
  * #58 Create king piece {Completed}
  * #57 Create bishop piece {Completed}
  * #56 Create knight piece {Completed}
  * #55 Create rook piece {Completed}
  * #54 Create pawn piece {Completed}
  * #28 Create portal piece {Completed}
* Be able to play a match according to the rules of Portal Chess
  * #30 Set game end condition {Completed} 
  * #25 Decide who goes first in the game {Completed}
  * #31 Send desired move to server {Completed}
  * #32 Recieve updated board {Completed}
  * #54, #55, #56, #57, #58, #59 update ChessPieces to interact with Portals

### Should have

As a user, I would like to...
* Accept invite to game sent by another player {Completed}
  * #53 Accept invitations {Completed}
* Reject invitations - user who sent request would be notified {Completed}
  * #35 Reject invitations {Completed}
* Would like to be able to close the match and come back to it later {Completed}
  * #17 Quit from game {Completed}
  * #61 Close match and return later {Completed}
* Know when a game is over and who has won/lost or if it has been abandoned {Completed}
  * #62 Know when game is over and who has won/lost or if abandoned {Completed}

### Could have

As a user, I would like to...
* Be able to log out {Completed}
* Unregister the account {Completed}
  * #13 Create unregister account button in UI {Completed}
* Switch between multiple games at once {Completed}
  * #36 Switch between multiple games {Completed}
* Abandon any game at any time {Completed}
  * #21 Abandon game option {Completed}

### Would have
* View my/another player's game history (players, start and end date/times, winner/loser of the match, whether a game was abandoned) on my/their profile
  * #78 Track an display user's gameplay statistics {Completed}
* Be able to tell whose turn it is {Completed}
  * #27 Implement turns {Completed}
