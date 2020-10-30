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
* Create a new match
  * #19 Create a button to start a chess match in UI {Completed}
  * #22 Create an invite to match screen {Completed}
  * #18 Create a new match in the system
* Invite friends/opponents to the match
  * #20 Seach users {Completed}
  * #77 Populate invitation list on the dashboard {Completed}
  * #52 Create inbox button {Completed}
  * #34 Send game invites
  * #23 Server handle game invite request 
* Start the game when enough people have joined
  * #65 Create board
  * #60 Initialize chess board
  * #59 Create queen piece
  * #58 Create king piece
  * #57 Create bishop piece
  * #56 Create knight piece
  * #55 Create rook piece
  * #54 Create pawn piece
  * #28 Create portal piece
* Be able to play a match according to the rules of Portal Chess
  * #30 Set game end condition
  * #25 Decide who goes first in the game
  * #31 Send desired move to server
  * #32 Recieve updated board

### Should have

As a user, I would like to...
* Accept invite to game sent by another player
  * #53 Accept invitations
* Reject invitations - user who sent request would be notified
  * #35 Reject invitations
* Would like to be able to close the match and come back to it later
  * #17 Quit from game
  * #61 Close match and return later
* Know when a game is over and who has won/lost or if it has been abandoned
  * #62 Know when game is over and who has won/lost or if abandoned

### Could have

As a user, I would like to...
* Be able to log out {Completed}
* Unregister the account
  * #13 Create unregister account button in UI
* Switch between multiple games at once
  * #36 Switch between multiple games
* Abandon any game at any time
  * #21 Abandon game option

### Would have
* View my/another player's game history (players, start and end date/times, winner/loser of the match, whether a game was abandoned) on my/their profile
  * #78 Track an display user's gameplay statistics
* Be able to tell whose turn it is
  * #27 Implement turns
