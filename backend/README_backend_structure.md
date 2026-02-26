# Platium Backend Structure

- [Platium Backend Structure](#platium-backend-structure)
- [Main project structure](#main-project-structure)
- [1. Models](#1-models)
   * [1.1 User](#11-user)
      + [Relations](#relations)
      + [Model representation](#model-representation)
   * [1.2 Game](#12-game)
      + [Relations](#relations-1)
      + [Model representation](#model-representation-1)
   * [1.3 Achievement](#13-achievement)
      + [Relations](#relations-2)
      + [Model representation](#model-representation-2)
   * [1.4 Genre & Category](#14-genre-category)
      + [Relations](#relations-3)
      + [Model representation](#model-representation-3)
   * [1.5 User Relations](#15-user-relations)
      + [Relations](#relations-4)
      + [Model representation](#model-representation-4)
   * [1.6 Platinum Models](#16-platinum-models)
      + [Relations](#relations-5)
      + [Model representation](#model-representation-5)
- [2. Controllers](#2-controllers)
   * [2.1 Auth Contoller](#21-auth-contoller)
      + [2.1.1 Register Endpoint](#211-register-endpoint)
         - [Request](#request)
         - [Ok Responses](#ok-responses)
         - [Bad Responses](#bad-responses)
      + [2.1.2 Login Endpoint](#212-login-endpoint)
         - [Request](#request-1)
         - [Ok Responses](#ok-responses-1)
         - [Bad Responses](#bad-responses-1)
      + [2.1.3 Link Steam Account Endpoint](#213-link-steam-account-endpoint)
         - [Request](#request-2)
         - [Ok Responses](#ok-responses-2)
         - [Bad Responses](#bad-responses-2)
   * [2.2 Platinum Users Contoller](#22-platinum-users-contoller)
      + [2.2.1 Search User by Triad Data Endpoint](#221-search-user-by-triad-data-endpoint)
         - [Request](#request-3)
         - [Ok Responses](#ok-responses-3)
         - [Bad Responses](#bad-responses-3)
      + [2.2.2 Get Authenticated User's Games Endpoint](#222-get-authenticated-users-games-endpoint)
         - [Request](#request-4)
         - [Ok Responses](#ok-responses-4)
         - [Bad Responses](#bad-responses-4)
      + [2.2.3 Get Authenticated User's Achievements for a Game Endpoint](#223-get-authenticated-users-achievements-for-a-game-endpoint)
         - [Request](#request-5)
         - [Ok Responses](#ok-responses-5)
         - [Bad Responses](#bad-responses-5)
      + [2.2.4 Get Authenticated User's All Achievements Endpoint](#224-get-authenticated-users-all-achievements-endpoint)
         - [Request](#request-6)
         - [Ok Responses](#ok-responses-6)
         - [Bad Responses](#bad-responses-6)
      + [2.2.5 Get Authenticated User Summary Endpoint](#225-get-authenticated-user-summary-endpoint)
         - [Request](#request-7)
         - [Ok Responses](#ok-responses-7)
         - [Bad Responses](#bad-responses-7)
      + [2.2.6 Get Specified User Games Endpoint](#226-get-specified-user-games-endpoint)
         - [Request](#request-8)
         - [Ok Responses](#ok-responses-8)
         - [Bad Responses](#bad-responses-8)
      + [2.2.7 Get Specified User Achievements for a Game Endpoint](#227-get-specified-user-achievements-for-a-game-endpoint)
         - [Request](#request-9)
         - [Ok Responses](#ok-responses-9)
         - [Bad Responses](#bad-responses-9)
      + [2.2.8 Get Specified User All Achievements Endpoint](#228-get-specified-user-all-achievements-endpoint)
         - [Request](#request-10)
         - [Ok Responses](#ok-responses-10)
         - [Bad Responses](#bad-responses-10)
      + [2.2.9 Get Specified User Summary Endpoint](#229-get-specified-user-summary-endpoint)
         - [Request](#request-11)
         - [Ok Responses](#ok-responses-11)
         - [Bad Responses](#bad-responses-11)
      + [2.2.10 Count Authenticated User Games](#2210-count-authenticated-user-games)
         - [Request](#request-12)
         - [Ok Responses](#ok-responses-12)
         - [Bad Responses](#bad-responses-12)
      + [2.2.11 Count Authenticated User Achievements](#2211-count-authenticated-user-achievements)
         - [Request](#request-13)
         - [Ok Responses](#ok-responses-13)
         - [Bad Responses](#bad-responses-13)
      + [2.2.12 Count Specified User Games](#2212-count-specified-user-games)
         - [Request](#request-14)
         - [Ok Responses](#ok-responses-14)
         - [Bad Responses](#bad-responses-14)
      + [2.2.13 Count Specified User Achievements](#2213-count-specified-user-achievements)
         - [Request](#request-15)
         - [Ok Responses](#ok-responses-15)
         - [Bad Responses](#bad-responses-15)
   * [2.3 Platinum Games Contoller](#23-platinum-games-contoller)
      + [2.3.1 Get Game by Steam API ID Endpoint](#231-get-game-by-steam-api-id-endpoint)
         - [Request](#request-16)
         - [Ok Responses](#ok-responses-16)
         - [Bad Responses](#bad-responses-16)
      + [2.3.2 Get Game Achievements by Steam API ID Endpoint](#232-get-game-achievements-by-steam-api-id-endpoint)
         - [Request](#request-17)
         - [Ok Responses](#ok-responses-17)
         - [Bad Responses](#bad-responses-17)
      + [2.3.3 Get Games with Pagination Endpoint](#233-get-games-with-pagination-endpoint)
         - [Request](#request-18)
         - [Ok Responses](#ok-responses-18)
         - [Bad Responses](#bad-responses-18)
   * [2.4 Steam Contoller](#24-steam-contoller)
      + [2.4.1 Get List of All Games Endpoint](#241-get-list-of-all-games-endpoint)
         - [Request](#request-19)
         - [Ok Responses](#ok-responses-19)
         - [Bad Responses](#bad-responses-19)
      + [2.4.2 Get Game Details Endpoint](#242-get-game-details-endpoint)
         - [Request](#request-20)
         - [Ok Responses](#ok-responses-20)
         - [Bad Responses](#bad-responses-20)
      + [2.4.3 Get User Summary by Steam ID Endpointt](#243-get-user-summary-by-steam-id-endpointt)
         - [Request](#request-21)
         - [Ok Responses](#ok-responses-21)
         - [Bad Responses](#bad-responses-21)
      + [2.4.4 Get User Games by Steam ID Endpoint](#244-get-user-games-by-steam-id-endpoint)
         - [Request](#request-22)
         - [Ok Responses](#ok-responses-22)
         - [Bad Responses](#bad-responses-22)
      + [2.4.5 Get User Recently Played Games by Steam ID Endpoint](#245-get-user-recently-played-games-by-steam-id-endpoint)
         - [Request](#request-23)
         - [Ok Responses](#ok-responses-23)
         - [Bad Responses](#bad-responses-23)
      + [2.4.6 Get User Achievements for a Specific Game by Steam ID Endpoint](#246-get-user-achievements-for-a-specific-game-by-steam-id-endpoint)
         - [Request](#request-24)
         - [Ok Responses](#ok-responses-24)
         - [Bad Responses](#bad-responses-24)
      + [2.4.7 Get All User Achievements by Steam ID Endpoint](#247-get-all-user-achievements-by-steam-id-endpoint)
         - [Request](#request-25)
         - [Ok Responses](#ok-responses-25)
         - [Bad Responses](#bad-responses-25)
- [3. Services](#3-services)
   * [3.1 Auth Service](#31-auth-service)
      + [3.1.1 Functions](#311-functions)
   * [3.2 Platinum Game Service](#32-platinum-game-service)
      + [3.2.1 Functions](#321-functions)
   * [3.3 Platinum Users Service](#33-platinum-users-service)
      + [3.3.1 Search Methods](#331-search-methods)
      + [3.3.2 Authenticated User Methods (`/users/me`)](#332-authenticated-user-methods-usersme)
   * [3.3.3 Specified User Methods (`/users/{steamId}`)](#333-specified-user-methods-userssteamid)
      + [3.3.4 Synchronization Logic](#334-synchronization-logic)
   * [3.4 Steam Service](#34-steam-service)
      + [3.4.1 Functions](#341-functions)
   * [3.5 Sync Service](#35-sync-service)
      + [3.5.1 Sync Player Games](#351-sync-player-games)
         - [3.5.1.1 Preparations](#3511-preparations)
      + [3.5.2 Sync Player Achievements](#352-sync-player-achievements)
         - [3.5.2.1 Preparations](#3521-preparations)
- [4. Config](#4-config)
   * [4.1 AppConfig](#41-appconfig)
      + [Purpose](#purpose)
   * [4.2 AsyncConfig](#42-asyncconfig)
      + [Purpose](#purpose-1)
      + [Why This Configuration Exists](#why-this-configuration-exists)
   * [4.3 OpenApiConfig](#43-openapiconfig)
      + [Purpose](#purpose-2)
      + [Security Configuration](#security-configuration)
   * [4.4 SteamProperties](#44-steamproperties)
      + [Annotations Explained](#annotations-explained)
      + [Properties Mapped](#properties-mapped)
- [5. Exceptions](#5-exceptions)
   * [5.1 GlobalExceptionHandler](#51-globalexceptionhandler)
      + [5.1.1 What It Does](#511-what-it-does)
      + [5.1.2 Why It Exists](#512-why-it-exists)
   * [5.2 Exceptions](#52-exceptions)
   * [6. Repositories](#6-repositories)
      + [6.1 Main Responsibilities](#61-main-responsibilities)
      + [6.2 Repositories used](#62-repositories-used)
- [7. Simple backend flow](#7-simple-backend-flow)
   * [7.1 Flow](#71-flow)
   * [7.2 Architectural Responsibilities Summary](#72-architectural-responsibilities-summary)
   * [7.3 Conclusion](#73-conclusion)

# Main project structure

```
backend
└───src
    ├───main
    │   ├───java
    │   │   └───daw
    │   │       └───pi
    │   │           └───platinum
    │   │               ├───config
    │   │               ├───controller
    │   │               ├───dto
    │   │               │   ├───platinum
    │   │               │   │   ├───achievement
    │   │               │   │   ├───auth
    │   │               │   │   ├───category
    │   │               │   │   ├───game
    │   │               │   │   ├───genre
    │   │               │   │   └───user
    │   │               │   └───steam
    │   │               │       ├───api
    │   │               │       │   ├───achievement
    │   │               │       │   ├───game
    │   │               │       │   └───user
    │   │               │       └───store
    │   │               │           └───game
    │   │               ├───exception
    │   │               ├───models
    │   │               ├───repository
    │   │               ├───security
    │   │               └───service
    │   └───resources
    └───test
        ├───java
        │   └───daw
        │       └───pi
        │           └───platinum
        └───resources

```

> [!NOTE]
> ### DTO structure reasoning
> The `/dto` folder inside the proyect has been organized in a way that allows us to know wether the information needed to build the DTO comes from **Steam** or **Platinum**.
>
> Inside the `/dto` we find:
> - `/platinumm`: The information needed to build this DTO is either managed by Platinum or comes directly from Platinum.
> - `/steam`: The information needed comes from steam, but it comes either from:
> - - `/store` **Store API** (Public API with harsher limits).
> - - `/api` **Steam API** (Private API that requires a prerequested key).
>

# 1. Models

All the models created in order to interact with the system.

## 1.1 User

This is the main model for the functioning of the website. It is used almost everywhere and contains all the information necesary fo the backend to comunicate with Steam. The information that this model contains is half managed by **Platinum** and the other part comes from **Steam** via the **account vinculation**.

### Relations

The `User` model has got relations with a lot of tables inside the Database:
- All user relations are managed by UserX entities explained [here](#15-user-relations) 

### Model representation

<details>
<summary>User</summary>

```java
public class User {

    private Long userId;                          // Primary Key | Auto-generated | Not nullable | Unique

    // Primary information saved at login
    private String username;    // Not nullable | VARCHAR(32)
    private String password;    // Not nullable | VARCHAR(64)
    private String email;       // Not nullable | Unique | VARCHAR(64)

    // Platinum managed data
    private Integer level;      // Not nullable | Default 0
    private Integer platinums;  // Not nullable | Default 0
    private String role;        // Not nullable | Default 'USER' | VARCHAR
    private boolean enabled;    // Not nullable | Default true

    // Creation and management data
    private LocalDateTime createdAt;    // Nullable | TIMESTAMP
    private LocalDateTime updatedAt;    // Nullable | TIMESTAMP

    // Steam recieved information
    private Long steamId;                         // Nullable | Unique (if enforced) | BIGINT
    private String personaName;                   // Nullable | VARCHAR
    private String profileUrl;                    // Nullable | VARCHAR
    private String avatar;                        // Nullable | VARCHAR
    private String avatarMedium;                  // Nullable | VARCHAR
    private String avatarFull;                    // Nullable | VARCHAR
    private Integer communityVisibilityState;     // Nullable | INT
    private Integer profileState;                 // Nullable | INT
    private Long timeCreated;                     // Nullable | BIGINT

    // Platinum information to update information
    private LocalDateTime lastTimeChecked;        // Not nullable | TIMESTAMP

    // Relations
    private List<UserGames> userGames;                  // One-to-Many | MappedBy user | LAZY
    private List<UserAchievements> userAchievements;    // One-to-Many | MappedBy user | LAZY
    private List<UserBadge> userBadges;                 // One-to-Many | MappedBy user | LAZY
    private List<PlatinumForum> forums;                 // One-to-Many | MappedBy user | LAZY
    private List<PlatinumForumReply> forumReplies;      // One-to-Many | MappedBy user | LAZY
    private List<UserRanking> userRankings;             // One-to-Many | MappedBy user | LAZY
}
```

</details>

## 1.2 Game

The `Game` model is the one in charge to store pretty much a lot of information of a game, like descriptions, image URLs... All the information is collected by the `sync service`, fetching data directly from **Steam** and later, if possible, from the **Database**.

> [!WARNING]
> Currently the `Game` model is not protected againts half data sent by **Steam**. This means that it is possible, right not, to be created with some field left blanc or with placeholder information due to miscomunication with the **Steam API** and **Steam Store**.
>
> This can be fixed by adding `flags` or `fields` that get turned on when one or more field are sent incorrectly by steam and then checking those field in order to try and fetch the information again.

### Relations

The `Game` model has various relations:
- [User](#11-user)
- [Achievement](#13-achievement)

### Model representation

<details>
<summary>Game</summary>

```java
public class Game {
    private Long gameId;    // Primary Key | Auto-generated | Not nullable | Unique

    // Steam fetched information
    private Integer apiId;                  // Not nullable | Unique
    private String name;                    // Not nullable | VARCHAR
    private String detailedDescription;     // Not nullable | TEXT
    private String shortDescription;        // Not nullable | TEXT
    private String headerImage;             // Not nullable | TEXT
    private String capsuleImage;            // Not nullable | TEXT
    private String capsuleImageV5;          // Not nullable | TEXT
    private Integer totalAchievements;      // Not nullable | Default 0
    private List<String> developers;        // ElementCollection | JoinTable game_developers | Not nullable
    private List<String> publishers;        // ElementCollection | JoinTable game_publishers | Not nullable
    private Boolean comingSoon;             // Not nullable | Default false
    private String date;                    // Not nullable | VARCHAR

    // Relations
    private Set<Genre> genres;              // Many-to-Many | JoinTable game_genres | Not nullable
    private Set<Category> categories;       // Many-to-Many | JoinTable game_categories | Not nullable
    private List<UserGames> userGames;      // One-to-Many | MappedBy game | Cascade ALL | OrphanRemoval true
    private List<Achievement> achievements; // One-to-Many | MappedBy game | Cascade ALL | OrphanRemoval true
}
```

</details>

## 1.3 Achievement

The `Achievement` model is responsible to store general information about one achievement of one game. The information needed to build this model is fetched from 2 diferent **Steam Endpoints**. The fetching is similar as that from the `Game` model, fetch from **Steam** and then, if possible, fetch from **Platinum Database**.

> [!WARNING]
> Currently the `Achievement` model is not protected againts half data sent by **Steam**. This means that it is possible, right not, to be created with some field left blanc or with placeholder information due to miscomunication with the **Steam API** and **Steam Store**.


### Relations

The `Achievement` model has various relations:
- [User](#11-user)
- [Game](#12-game)

### Model representation


<details>
<summary>Achievement</summary>

```java
public class Achievement {

    private Long achievementId;     // Primary Key | Auto-generated | Not nullable | Unique

    // Steam fetched information
    private String name;                    // Not nullable | VARCHAR
    private String displayName;             // Not nullable | VARCHAR
    private String description;             // Nullable | VARCHAR
    private Boolean hidden;                 // Not nullable | BOOLEAN
    private String icon;                    // Not nullable | VARCHAR
    private String iconGray;                // Not nullable | VARCHAR
    private Float playerPercentUnlocked;    // Nullable | FLOAT

    // Relations
    private Game game;                                  // Many-to-One | JoinColumn game_id | Not nullable | LAZY
    private List<UserAchievements> userAchievements;    // One-to-Many | MappedBy achievement | LAZY
}
```

</details>

## 1.4 Genre & Category

Both `Genre` & `Category` are listed here together because they store essentially the same information. Bot get their information when a `Game` model is created and only save, for now, the name of the genre/category they are tied to.

> [!NOTE]
> The reason behind why `Genre` and `Category` have separated models is because in the future it is possible that each one gets extra information in their model, like extra information for the Category or icons for the genre...

### Relations

The `Genre`/`Category` model one relation:
- [Game](#12-game)

### Model representation


<details>
<summary>Genre & Category</summary>

```java
public class Genre/Category {

    private Long genreId;              // Primary Key | Auto-generated | Not nullable | Unique

    // Data fetched from steam
    private Integer apiId;             // Not nullable | Unique
    private String description;        // Not nullable | VARCHAR

    // Relation
    private Set<Game> games;           // Many-to-Many | MappedBy genres
}
```

</details>

## 1.5 User Relations

Here are all `UserX` models that are used to bond together various models with the `User` model in order to create the relations. Usually this relations are created because of the need to add information to the relation, like for `UserGames` to get is the user has played the game recently...

The interesting side of the `UserX` models is that, when the information comes from steam, due to them bein **very volatile** and **prone to change**, a whole `sync` system has been implemented using the `lastTimeChecked` value and applying time **thresholds**.

### Relations

The `User Relations` models have various relations:
- [User](#11-user)
- [Game](#12-game)
- [Achievement](#13-achievement)
- [PlatinumBadge](#16-platinum-models)
- [PlatinumRanking](#16-platinum-models)

### Model representation


<details>
<summary>UserGames</summary>

```java
public class UserGames {

    private Long ugId;                      // Primary Key | Auto-generated | Not nullable | Unique

    // Information fetched from steam
    private Integer playtimeForever;        // Not nullable | Default 0
    private Long timeLastPlayed;            // Not nullable | Default 0

    // Data used by Platinum to update the model
    private LocalDateTime lastTimeChecked;  // Not nullable | TIMESTAMP

    // Relations
    private User user;                      // Many-to-One | JoinColumn user_id | LAZY
    private Game game;                      // Many-to-One | JoinColumn game_id | LAZY
}
```

</details>

<details>
<summary>UserAchievements</summary>

> It is in mind that this relation needs more information, and it is planed to fetch also the user's unlock time for each achievement.

```java
public class UserAchievements {

    private Long uaId;                      // Primary Key | Auto-generated | Not nullable | Unique

    // Data used by Platinum to update the model
    private LocalDateTime lastTimeChecked;  // Not nullable | TIMESTAMP

    // Relations
    private User user;                      // Many-to-One | JoinColumn user_id | LAZY
    private Achievement achievement;        // Many-to-One | JoinColumn achievement_id | LAZY
}
```

</details>

<details>
<summary>UserBadge</summary>

> Not implemented

```java
public class UserBadge {

    private Long ubId;              // Primary Key | Auto-generated | Not nullable | Unique

    // Data managed by Platinum
    private LocalDate unlockedDate; // Not nullable | DATE

    // Relations
    private User user;              // Many-to-One | JoinColumn user_id | LAZY
    private PlatinumBadge badge;    // Many-to-One | JoinColumn badge_id | LAZY
}
```

</details>

<details>
<summary>UserRanking</summary>

> Not implemented

```java
public class UserRanking {

    private Long urId;                  // Primary Key | Auto-generated | Not nullable | Unique

    // Data managed by Platinum
    private Integer position;           // Not nullable | INT

    // Relations
    private User user;                  // Many-to-One | JoinColumn user_id | Not nullable | LAZY
    private PlatinumRanking ranking;    // Many-to-One | JoinColumn ranking_id | Not nullable | LAZY
}
```

</details>

## 1.6 Platinum Models

The `Platinum Models` are a colection of models that intend to simulate systems such as rankings, points and forums.

> [!WARNING]
> Not implemented.
>
> Unfortunatelly, due to time limitations we decided to focus first on the **Steam** driven side of the website before jumping in on ur models.
>
> *Models represented here can be subjected to ***heavy*** changes*.

### Relations

The `Platinum Models` have various relations:
- **Inner relations**
- [User](#11-user)
- [UserBadge](#15-user-relations)
- [UserRanking](#15-user-relations)

### Model representation


<details>
<summary>PlatinumBadge</summary>

> Not implemented

```java
public class PlatinumBadge {

    private Long badgeId;           // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private String name;            // Not nullable | VARCHAR(255)
    private String description;     // Not nullable | TEXT

    // Relations
    private List<PlatinumBadgeChallenge> badgeChallenges;   // One-to-Many | MappedBy badge | LAZY
    private List<UserBadge> userBadges;                     // One-to-Many | MappedBy badge | LAZY
}
```

</details>

<details>
<summary>PlatinumBadgeChallenge</summary>

> Not implemented

```java
public class PlatinumBadgeChallenge {

    private Long bcId;                               // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private Integer progress;                        // Not nullable | Default 0 | INT
    private LocalDate completedDate;                 // Nullable | DATE

    // Relations
    private PlatinumBadge badge;                     // Many-to-One | JoinColumn badge_id | LAZY
    private PlatinumChallenge challenge;             // Many-to-One | JoinColumn challenge_id | LAZY
}
```

</details>

<details>
<summary>PlatinumChallenge</summary>

> Not implemented

```java
public class PlatinumChallenge {

    private Long challengeId;                        // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private String title;                            // Not nullable | VARCHAR(255)
    private String description;                      // Not nullable | TEXT
    private Integer requirement;                     // Not nullable | Min 1 | INT
    private Integer points;                          // Not nullable | Default 20 | INT

    // Relations
    private List<PlatinumBadgeChallenge> badgeChallenges; // One-to-Many | MappedBy challenge | LAZY
    private PlatinumBadge badge;                     // Many-to-One | JoinColumn badge_id | LAZY
}
```

</details>

<details>
<summary>PlatinumForum </summary>

> Not implemented

```java
public class PlatinumForum {

    private Long forumId;                            // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private String title;                            // Not nullable | VARCHAR(255)
    private String content;                          // Not nullable | TEXT
    private LocalDate creationDate;                  // Not nullable | DATE

    // Relations
    private User user;                               // Many-to-One | JoinColumn user_id | LAZY
    private List<PlatinumForumReply> replies;       // One-to-Many | MappedBy forum | LAZY
}
```

</details>

<details>
<summary>PlatinumForumReply</summary>

> Not implemented

```java
public class PlatinumForumReply {

    private Long forumRepliesId;                     // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private String content;                          // Not nullable | TEXT
    private LocalDate creationDate;                  // Not nullable | DATE

    // Relations
    private PlatinumForum forum;                     // Many-to-One | JoinColumn forum_id | LAZY
    private User user;                               // Many-to-One | JoinColumn user_id | LAZY
}
```

</details>

<details>
<summary>PlatinumRanking</summary>

> Not implemented

```java
public class PlatinumRanking {

    private Long rankingId;                          // Primary Key | Auto-generated | Not nullable | Unique

    // Platinum data
    private String title;                            // Not nullable | VARCHAR(255)
    private String description;                      // Not nullable | VARCHAR(1000)

    // Relations
    private List<UserRanking> userRankings;         // One-to-Many | MappedBy ranking | LAZY | Cascade ALL
}
```

</details>

# 2. Controllers

Here are al the controllers created and explained with the endpoints that they manage.

## 2.1 Auth Contoller

This is the only public endpoint managed by Platinum. It is used to **register users**, **perform login**, **link steam account**.

**Base URL**: `/auth`

### 2.1.1 Register Endpoint

Add a new user to the database with its username, password, and email as reference.

**Endpoint**: `/auth/register`

#### Request

<details>
<summary>Request example</summary>

```bash
POST /auth/register
Content-Type: application/json

{
  "username": "user",
  "password": "user123",
  "email": "user@gmail.com"
}
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "message": "User created successfully"
}
```

</details>

#### Bad Responses

**400 - Bad Request**
Invalid data.

**409 - Duplicated User**
User data is already present in the database. Attempting to create a duplicated user.

### 2.1.2 Login Endpoint

Login a user comparing the introduced credentials with the ones of a registered user.

**Endpoint**: `/auth/login`

#### Request

<details>
<summary>Request example</summary>

```bash
POST /auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "user123"
}
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "user",
  "email": "user@gmail.com",
  "role": "USER",
  "enabled": true
}
```

</details>

#### Bad Responses

**400 - Bad Request**
Username or password incorrect.

**404 - Not Found**
User not found.

### 2.1.3 Link Steam Account Endpoint

Link the current logged user to a Steam account via `steam_id`.

**Endpoint**: `/auth/linking`

#### Request

<details>
<summary>Request example</summary>

```bash
PUT /auth/linking
Content-Type: application/json

{
  "steam_id": "76561198000000000"
}
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "message": "Linking successful"
}
```

</details>

#### Bad Responses

**404 - Not Found**
User not found.

## 2.2 Platinum Users Contoller

Platinum user focused controller. The enpoints related to this controller are intended to manage authenticated users of the Platinum website.

**Base URL**: `/users`

> [!NOTE]
> The autheinticated user and speficied user endpoints are basically the same, only changing the requirements inthe path. They return the same DTOs.

### 2.2.1 Search User by Triad Data Endpoint

Search users inside the Platinum database by the triad data (Username, Steam Name, Steam Id).

> [!NOTE]
> This endpoint is **case-sensitive**

**Endpoint**: `/users/search`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/search?searchParam=some_user
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
[
  {
    "id": 1,
    "username": "example_user",
    "email": "user@example.com",
    "steamName": "SteamUser123",
    "steamId": "76561198000000000"
  },
  {
    "id": 2,
    "username": "another_user",
    "email": "another@example.com",
    "steamName": "SteamUser456",
    "steamId": "76561198000000001"
  }
]
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.2.2 Get Authenticated User's Games Endpoint

Get all the games from the authenticated user.

**Endpoint**: `/users/me/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/games
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
[
  {
    "gameId": 101,
    "name": "Game 1",
    "hoursPlayed": 42,
    "achievementsUnlocked": 15,
    "totalAchievements": 20
  },
  {
    "gameId": 102,
    "name": "Game 2",
    "hoursPlayed": 7,
    "achievementsUnlocked": 2,
    "totalAchievements": 10
  }
]
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's games not found.

### 2.2.3 Get Authenticated User's Achievements for a Game Endpoint

Get all the achievements from a specified game for the authenticated user.

**Endpoint**: `/users/me/games/{apiId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/games/{apiId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "gameId": 101,
  "gameName": "Cyber Adventure",
  "achievements": [
    {
      "achievementId": "achv_001",
      "name": "First Steps",
      "description": "Complete the tutorial",
      "unlocked": true,
      "unlockTime": "2026-02-10T14:30:00"
    },
    {
      "achievementId": "achv_002",
      "name": "Explorer",
      "description": "Discover all areas",
      "unlocked": false,
      "unlockTime": null
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's achievements for game not found.

### 2.2.4 Get Authenticated User's All Achievements Endpoint

Get all the achievements from the authenticated user.

**Endpoint**: `/users/me/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
[
  {
    "achievementId": "achv_001",
    "name": "First Steps",
    "description": "Complete the tutorial",
    "unlocked": true,
    "unlockTime": "2026-02-10T14:30:00",
    "gameId": 101,
    "gameName": "Cyber Adventure"
  },
  {
    "achievementId": "achv_002",
    "name": "Explorer",
    "description": "Discover all areas",
    "unlocked": false,
    "unlockTime": null,
    "gameId": 102,
    "gameName": "Mystery Quest"
  }
]
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's achievements not found.

### 2.2.5 Get Authenticated User Summary Endpoint

Get summary information from the authenticated user.

**Endpoint**: `/users/me/summary`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/summary
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "id": 1,
  "username": "example_user",
  "email": "user@example.com",
  "steamName": "SteamUser123",
  "steamId": "76561198000000000",
  "roles": ["USER"],
  "createdAt": "2026-01-27T10:30:00",
  "updatedAt": "2026-02-20T15:45:00"
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.2.6 Get Specified User Games Endpoint

Get all the games from the specified user by `steamId`.

**Endpoint**: `/users/{steamId}/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/games
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
[
  {
    "gameId": 101,
    "name": "Game 1",
    "hoursPlayed": 42,
    "achievementsUnlocked": 15,
    "totalAchievements": 20
  },
  {
    "gameId": 102,
    "name": "Game 2",
    "hoursPlayed": 7,
    "achievementsUnlocked": 2,
    "totalAchievements": 10
  }
]
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's games not found.

### 2.2.7 Get Specified User Achievements for a Game Endpoint

Get all the achievements from a specified game for the specified user by `steamId`.

**Endpoint**: `/users/{steamId}/games/{apiId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/games/{apiId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "gameId": 101,
  "gameName": "Cyber Adventure",
  "achievements": [
    {
      "achievementId": "achv_001",
      "name": "First Steps",
      "description": "Complete the tutorial",
      "unlocked": true,
      "unlockTime": "2026-02-10T14:30:00"
    },
    {
      "achievementId": "achv_002",
      "name": "Explorer",
      "description": "Discover all areas",
      "unlocked": false,
      "unlockTime": null
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's achievements for game not found.

### 2.2.8 Get Specified User All Achievements Endpoint

Get all the achievements from the authenticated user.

**Endpoint**: `/users/{steamId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
[
  {
    "achievementId": "achv_001",
    "name": "First Steps",
    "description": "Complete the tutorial",
    "unlocked": true,
    "unlockTime": "2026-02-10T14:30:00",
    "gameId": 101,
    "gameName": "Cyber Adventure"
  },
  {
    "achievementId": "achv_002",
    "name": "Explorer",
    "description": "Discover all areas",
    "unlocked": false,
    "unlockTime": null,
    "gameId": 102,
    "gameName": "Mystery Quest"
  }
]
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User's achievements not found.

### 2.2.9 Get Specified User Summary Endpoint

Get summary information from the authenticated user.

**Endpoint**: `/users/{steamId}/summary`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/summary
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "id": 1,
  "username": "example_user",
  "email": "user@example.com",
  "steamName": "SteamUser123",
  "steamId": "76561198000000000",
  "roles": ["USER"],
  "createdAt": "2026-01-27T10:30:00",
  "updatedAt": "2026-02-20T15:45:00"
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.2.10 Count Authenticated User Games
Get total games from the authenticated user.
**Endpoint:** `/users/me/count/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/count/games
Authorization: Bearer token_here
```
</details>

#### Ok Responses
**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "count": 183
}
```
</details>

#### Bad Responses
**403 - Forbiden** User not authorized.
**404 - Not Found** User not Found.

### 2.2.11 Count Authenticated User Achievements
Get total achievements from the authenticated user.
**Endpoint:** `/users/me/count/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/me/count/achievements
Authorization: Bearer token_here
```
</details>

#### Ok Responses
**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "count": 2382
}
```
</details>

#### Bad Responses
**403 - Forbiden** User not authorized.
**404 - Not Found** User not Found.

### 2.2.12 Count Specified User Games
Get total games from a specified user.
**Endpoint:** `users/{steamId}/count/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/count/achievements
Authorization: Bearer token_here
```
</details>

#### Ok Responses
**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "count": 23
}
```
</details>

#### Bad Responses
**403 - Forbiden** User not authorized.
**404 - Not Found** User not Found.

### 2.2.13 Count Specified User Achievements
Get total achievements from a specified user.
**Endpoint:** `users/{steamId}/count/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /users/{steamId}/count/achievements
Authorization: Bearer token_here
```
</details>

#### Ok Responses
**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "count": 1812
}
```
</details>

#### Bad Responses
**403 - Forbiden** User not authorized.
**404 - Not Found** User not Found.

## 2.3 Platinum Games Contoller

Platinum games focused controller. The enpoints related to this controller are intended to manage the existing games inside the Platinum database.

**Base URL**: `/games`

### 2.3.1 Get Game by Steam API ID Endpoint

Get a game from the database via its Steam API ID.

**Endpoint**: `/games/{apiId}`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /games/{apiId}
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "gameId": 101,
  "name": "Cyber Adventure",
  "developer": "Fictional Studios",
  "publisher": "Fictional Publisher",
  "releaseDate": "1 JAN 2025",
  "genres": ["Adventure", "RPG"],
  "hoursPlayed": 42,
  "achievementsUnlocked": 15,
  "totalAchievements": 20
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
Game not found.

### 2.3.2 Get Game Achievements by Steam API ID Endpoint

Get all game's achievements from the database via its Steam API ID.

**Endpoint**: `/games/{apiId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /games/{apiId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "gameId": 101,
  "gameName": "Cyber Adventure",
  "achievements": [
    {
      "name": "achv_001",
      "displayName": "First Steps",
      "description": "Complete the tutorial",
      "hidden": true,
      "icon": "icon_url",
      "iconGray": "icon_gray_url",
      "playerPercentageUnlocked": "20.2%"
    },
    {
      "name": "achv_002",
      "displayName": "Complete Campaign",
      "description": "Complete the main campaign",
      "hidden": false,
      "icon": "icon_url",
      "iconGray": "icon_gray_url",
      "playerPercentageUnlocked": "70.2%"
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
Game not found.

### 2.3.3 Get Games with Pagination Endpoint

Get all games from the database applying simple pagination.

**Endpoint**: `/games/`
**Endpoint with variables**: `/games/?page=0&size=20`
**Variables**:

#### Request

<details>
<summary>Request example</summary>

```bash
GET /games/?page=0&size=20
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "content": [
    {
      "gameId": 12345,
      "name": "Cyber Adventure",
      "developer": "Fictional Studios",
      "publisher": "Fictional Publisher",
      "releaseDate": "1 JAN 2025"
    },
    {
      "gameId": 12346,
      "name": "Mystery Quest",
      "developer": "Adventure Devs",
      "publisher": "Mystery Publisher",
      "releaseDate": "15 JAN 2025"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 5,
  "totalElements": 100,
  "last": false,
  "first": true,
  "numberOfElements": 20,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  }
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

## 2.4 Steam Contoller

Steam interaction based controller. The endpoints related to this controller are used to comunicate with Steam API and Steam Store to fetch live and updated information to store in the database.

**Base URL**: `/steam`

### 2.4.1 Get List of All Games Endpoint

Get all the apps listed on Steam.

> [!CAUTION]
> This endpoint asks for **1000 steam random games**. Currently **unused**.

**Endpoint**: `/steam/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/games
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "applist": {
    "apps": [
      {
        "appid": 12345,
        "name": "Cyber Adventure"
      },
      {
        "appid": 12346,
        "name": "Mystery Quest"
      }
    ]
  }
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

### 2.4.2 Get Game Details Endpoint

Get details of one game by its Steam API ID.

> [!IMORTANT]
> This endpoint recieves it's information from the **public** Steam Store API. It is much more **unstable**.

> [!NOTE]
> This enpoint is used to create the model of `Game`.

**Endpoint**: `/steam/games/{apiId}`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/games/{apiId}
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "12345": {
    "success": true,
      "data": {
        "steam_appid": 1073741824,
        "name": "Game 1",
        "detailed_description": "Description of game but with details",
        "short_description": "Description, very sho...",
        "header_image": "url",
        "capsule_image": "url",
        "capsule_imagev5": "url",
        "categories": [
          {
            "id": 2,
            "description": "string"
          }
        ],
        "genres": [
          {
            "id": 13,
            "description": "string"
          }
        ],
        "achievements": {
          "statid": 1073741824,
          "bit": 1073741824,
          "name": "string",
          "desc": "string",
          "icon": "string",
          "icon_gray": "string",
          "hidden": true,
          "player_percent_unlocked": 0.1
        },
        "developers": [
          "string"
        ],
        "publishers": [
          "string"
        ],
        "release_date": {
          "coming_soon": true,
          "date": "string"
        }
      }
  }
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
Game not found.

### 2.4.3 Get User Summary by Steam ID Endpointt

Get user summary by its Steam ID.

**Endpoint**: `/steam/players/{steamId}/summary`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/players/{steamId}/summary
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "steamId": "76561198000000000",
  "username": "SteamUser123",
  "profileUrl": "https://steamcommunity.com/id/SteamUser123/",
  "avatarUrl": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/xx/xxxx.jpg",
  "realName": "John Doe",
  "countryCode": "US",
  "lastLogOff": "2026-02-20T15:45:00",
  "accountCreated": "2020-05-10T12:00:00"
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.4.4 Get User Games by Steam ID Endpoint

Get all user games by its Steam ID.

> [!NOTE]
> This enpoint is used to create the relations in `UserGames`.

**Endpoint**: `/steam/players/{steamId}/games`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/players/{steamId}/games
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "steamId": "76561198000000000",
  "games": [
    {
      "appid": 12345,
      "name": "Cyber Adventure",
      "playtimeForever": 420,
      "playtimeWindowsForever": 400,
      "achievementsUnlocked": 15,
      "totalAchievements": 20,
      "iconUrl": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/12345/icon.jpg"
    },
    {
      "appid": 12346,
      "name": "Mystery Quest",
      "playtimeForever": 120,
      "playtimeWindowsForever": 120,
      "achievementsUnlocked": 2,
      "totalAchievements": 10,
      "iconUrl": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/12346/icon.jpg"
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.4.5 Get User Recently Played Games by Steam ID Endpoint

Get user recently played games by its Steam ID.

> [!NOTE]
> This endpoint is currently **NOT** used. It is intended to be used to add more information in `UserGames`.

**Endpoint**: `/steam/players/{steamId}/games/recent`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/players/{steamId}/games/recent
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "steamId": "76561198000000000",
  "recentGames": [
    {
      "appid": 12345,
      "name": "Cyber Adventure",
      "playtime2Weeks": 20,
      "playtimeForever": 420,
      "iconUrl": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/12345/icon.jpg"
    },
    {
      "appid": 12346,
      "name": "Mystery Quest",
      "playtime2Weeks": 10,
      "playtimeForever": 120,
      "iconUrl": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/apps/12346/icon.jpg"
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

### 2.4.6 Get User Achievements for a Specific Game by Steam ID Endpoint

Get user achievements for one specific game specified with its Steam API ID, identifying the user via its Steam ID.

> [!NOTE]
> This enpoint is used to create the relations in `UserAchievemnts` and to determine what achievements does the user have unlocked.

**Endpoint**: `/steam/players/{steamId}/games/{apiId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/players/{steamId}/games/{apiId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "steamId": "76561198000000000",
  "gameId": 12345,
  "gameName": "Cyber Adventure",
  "achievements": [
    {
      "achievementId": "achv_001",
      "name": "First Steps",
      "description": "Complete the tutorial",
      "unlocked": true,
      "unlockTime": "2026-02-10T14:30:00"
    },
    {
      "achievementId": "achv_002",
      "name": "Explorer",
      "description": "Discover all areas",
      "unlocked": false,
      "unlockTime": null
    }
  ]
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User/Game not found.

### 2.4.7 Get All User Achievements by Steam ID Endpoint

Get all user achievements by its Steam ID.

> [!NOTE]
> This enpoint is used to create the relations in `UserAchievements`.

**Endpoint**: `/steam/players/{steamId}/achievements`

#### Request

<details>
<summary>Request example</summary>

```bash
GET /steam/players/{steamId}/achievements
Authorization: Bearer token_here
```

</details>

#### Ok Responses

**200 - Ok**

<details>
<summary>200 Response</summary>

```json
{
  "steamId": "76561198000000000",
  "achievementsByGame": {
    "12345": [
      {
        "achievementId": "achv_001",
        "name": "First Steps",
        "description": "Complete the tutorial",
        "unlocked": true,
        "unlockTime": "2026-02-10T14:30:00"
      },
      {
        "achievementId": "achv_002",
        "name": "Explorer",
        "description": "Discover all areas",
        "unlocked": false,
        "unlockTime": null
      }
    ],
    "12346": [
      {
        "achievementId": "achv_010",
        "name": "Beginner",
        "description": "Finish first mission",
        "unlocked": true,
        "unlockTime": "2026-01-15T11:00:00"
      }
    ]
  }
}
```

</details>

#### Bad Responses

**403 - Forbiden**
User not authorized.

**404 - Not Found**
User not found.

# 3. Services

The services for Platinum are very simple to understand. 
There are 2 main types of services:
- **Controller Service**: They are there to fetch a controller endpoint request and manage the data that needs procesing. After that they return the data so that the controller can return the necessary HTTP Response.
- **Logic Service**: They are used by any part of the backend to perform logical operations and interact, mainly, with the database. The only service of this type is `syncService`, a very intricate and complex service in charge of the sync operations for the data in the database and comunication with Steam.

## 3.1 Auth Service

The `AuthService` is a **Controller Service**.

It is responsible for all the authentication (**login** & **register**) logic and also manages the **steam sync** logic.

### 3.1.1 Functions

**Register**
The register only checks if the passed data is unique and doesent exist in the database:
- In case it is unique it saves the new user in the databse and returns a ok message.
- In case of having a user in the database it returns a 409 error.

**Login**
The login fetches the data that is sent. It creates a JWT token, and checks if the user is existent in the database:
- If the user exists it returns a copy of the user so that he frontend knows it'ss information.
- In case the user doesent exists it returns an error code.

**Linking**
At the linking method, the service recieves the request sent by the frontend. The method checks if the user exists:
- If the user exists it saves the steamId and returns ok.
- If the user is unexisten the service returns an error.

## 3.2 Platinum Game Service

The `GameService` is a **Controller Service**.

It is responsible for handling all the game-related logic, including retrieving games by their external API identifier, fetching achievements, and providing paginated game listings.

---

### 3.2.1 Functions

**Get Game By ApiId**

This method retrieves a game using its external API identifier (`apiId`).

- It queries the database using `gameRepository.findByApiId(apiId)`.
- If the game exists, it wraps the entity inside a `GameResponse` object and returns it.
- If the game does not exist, it throws a `GameApiIdNotFoundException`.

This ensures that:
- Only valid and existing games are returned.
- Non-existent identifiers are properly handled with a domain-specific exception.

---

**Get Game Achievements By ApiId**

This method retrieves all achievements associated with a specific game identified by its `apiId`.

- It queries the database using `achievementRepository.findByGameApiId(apiId)`.
- If no game is found, it throws a `GameApiIdNotFoundException`.
- If the game exists:
  - The achievements are mapped into `AchievementDTO` objects.
  - The list of DTOs is wrapped inside an `AchievementResponse`, which includes:
    - The `apiId` of the game.
    - The list of mapped achievements.

This method:
- Separates entity logic from response logic using DTOs.
- Ensures structured and frontend-friendly achievement data.

---

**Get Paginated All Games**

This method retrieves all games in a paginated format.

- It receives a `Pageable` object containing pagination parameters (page number, size, sorting).
- It executes `gameRepository.findAllGameSummariesDTO(pageable)` to retrieve a paginated list of `GameSummaryResponse`.
- It returns a `Page<GameSummaryResponse>` object.

This method:
- Supports efficient pagination.
- Avoids loading full game entities when only summary data is required.
- Is optimized for large datasets using transaction-safe and pageable queries.

## 3.3 Platinum Users Service

The `PlatinumUsersService` is a **Controller Service**.

It is responsible for handling all user-related logic, including:

- Searching users.
- Retrieving authenticated user data.
- Retrieving public data from a specific user (via `steamId`).
- Managing conditional Steam synchronization (games, achievements, and summary).
- Counting user statistics.

This service interacts with:

- `UserRepository`
- `UserGameRepository`
- `UserAchievementRepository`
- `AchievementRepository`
- `SyncService`

### 3.3.1 Search Methods

**Find Users By Triad**

Searches users by a triad parameter:

- Username  
- Steam username  
- Steam ID  

Behavior:
- If `searchParam` is not null or blank:
  - Executes `userRepository.findUsersByTriad(searchParam)`.
  - If no users are found, throws `UserNotFoundTriadException`.
- If the parameter is null or blank:
  - Returns `null`.

### 3.3.2 Authenticated User Methods (`/users/me`)

These methods operate using the currently authenticated user obtained from `SecurityContextHolder`.

All methods:
1. Retrieve the authenticated username.
2. Fetch the user from the database.
3. Throw `UsernameNotFoundException` if the user does not exist.

**Get Authenticated User Games**

Returns all games owned by the authenticated user.

Flow:
- Checks if a Steam sync is required (`needsSteamSync`, 2-hour threshold).
- If required, executes `syncService.syncPlayerGames(user)`.
- Retrieves user games from `userGameRepository`.
- Maps them into `GameResponse`.

**Get Authenticated User Achievements for a Specific Game**

Returns all achievements for a given `apiId`, including unlock status.

Flow:
- Validates authenticated user.
- Checks if game sync is required.
- Checks if achievement sync is required.
- Retrieves:
  - All game achievements.
  - User unlocked achievements for that game.
- Creates a comparison map using `displayName`.
- Builds a `Set<AchievementUnlockedDTO>` where:
  - `true` → unlocked
  - `false` → locked
- Returns `AchievementUnlockedResponse`.

Throws:
- `GameApiIdNotFoundException` if the game does not exist.

**Get All Authenticated User Achievements**

Returns all achievements unlocked by the authenticated user, grouped by game.

Flow:
- Checks if achievement sync is required.
- Retrieves all `UserAchievements`.
- Groups achievements by `game.apiId`.
- Maps them into `AchievementDTO`.
- Converts the grouped map into a `List<AchievementResponse>`.

**Get Authenticated User Summary**

Returns a summary of the authenticated user.

Flow:
- Checks if summary sync is required.
- Executes `syncService.syncPlayerSummary(user)` if necessary.
- Returns `UserResponse`.

**Count Authenticated User Games**

Returns the total number of games owned by the authenticated user.

- Uses `userGameRepository.countByUserUserId`.

**Count Authenticated User Achievements**

Returns the total number of achievements unlocked by the authenticated user.

- Uses `userAchievementRepository.countByUserUserId`.

## 3.3.3 Specified User Methods (`/users/{steamId}`)

These methods operate on a user identified by `steamId`.

All methods:
- Retrieve the user via `userRepository.findBySteamId`.
- Throw `SteamIdNotFoundException` if the user does not exist.

**Get User Games (By SteamId)**

- Checks if game sync is required (2-hour threshold).
- Executes `syncService.syncPlayerGames(user)` if necessary.
- Returns `List<GameResponse>`.

**Get User Achievements for a Specific Game (By SteamId)**

Returns all achievements of a game for a specific user, including unlock status.

Flow:
- Validates user via `steamId`.
- Checks game sync.
- Checks achievement sync.
- Retrieves:
  - All game achievements.
  - User unlocked achievements.
- Builds unlock comparison.
- Returns `AchievementUnlockedResponse`.

Throws:
- `GameApiIdNotFoundException` if the game does not exist.

**Get All User Achievements (By SteamId)**

Returns all unlocked achievements grouped by game.

Flow:
- Checks achievement sync.
- Retrieves all `UserAchievements`.
- Groups by `game.apiId`.
- Maps into `AchievementDTO`.
- Returns `List<AchievementResponse>`.

**Get User Summary (By SteamId)**

- Checks if summary sync is required.
- Executes `syncService.syncPlayerSummary(user)` if necessary.
- Returns `UserResponse`.

**Count User Games (By SteamId)**

Returns the total number of games owned by the specified user.

- Uses `userGameRepository.countByUserUserId`.

**Count User Achievements (By SteamId)**

Returns the total number of achievements unlocked by the specified user.

- Uses `userAchievementRepository.countByUserUserId`.

### 3.3.4 Synchronization Logic

The service uses a **conditional Steam synchronization strategy**:

- Sync only runs if the last sync is older than a defined threshold.
- Games and achievements use a 2-hour threshold.
- Summary sync may use a different threshold.
- Prevents unnecessary Steam API calls.
- Ensures data consistency without degrading performance.

## 3.4 Steam Service

This service's main focus is on managing the **game** and **achievement** data received from the Steam Api and its relation to a **user**. 

It uses the Steam `key` in order to validate the requests done to the Api.

### 3.4.1 Functions

**getGames**
Calls the **GetAppList**:
- Retrieves a list of games.

**getGameDetails**
Calls the **appdetails** by the inputed `appid`:
- Retrieves a specific **game** object and its data.

**getPlayerSummary**
Calls the **GetPlayerSummaries** by the inputed `steamid`:
- Retrieves a specific Steam **user** information.

**getPlayersGames**
Gets all the **games** related to a specific **user**:
- Returns a url with specifications about the request.

For this method, there are multiple boolean checks, that edit the returned attributes depending on the result:
- `includeAppInfo`: return information of the **game** with the url
- `includePlayedFreeGames`: return free **games** too with the url
- `skipUnvettedApps`: don't include vetted **games**
- `includeExtendedInfo`: return detailed information about the **game** with the url

**getPlayerGamesRecent**
Gets the last *(**number** indicated by the parameter)* **games** 
- Retrieves a list of the last **games** (list's size determined by the **number** inputed).

**getPlayerGamesAchievements**
From the indicated **user** and a single **game** (`steamid` and `appid`)...
- Retrieve a list of **achievements** and the state the **user** has it on.

**getPlayersAchievements**
From the indicated **user** and list of **games** ...
- Retrieve a list of **achievements** and the state the **user** has it on for every **game**.

**getPlayerGamesAchievementsDetails**
From the indicated **user** and **game**...
- Retrieve the details of the **achievements**

## 3.5 Sync Service

The `SyncService` is the only **Logic Service**.

> [!IMPORTANT]
> Right now the service works as a "missing entry fetcher". That means that right now it fetches **only** data that is **not present** in the database. Due to the complexity it has been left as this for now.
>
> The original plan is to make this service as a **information updater** too. So when the threshold is reached, it will check if the games and achievements need to get their **informaiton updated** and if entries are **uncomplete** it will also check with steam if the information can be fetched.

It is responsible for:
- Check if `Games` and `Achievements`, independently, in the database need to get their information updated.
- Check if `Games` and `Achievements` are missing in the database.
- Update `UserGames` and `UserAchievements` relations.
- Fetch information for `User` once the **profile linking** is successfull or the time threshold is reached.

> [!NOTE]
> This service's **complexity** is increased due to:
> - The usage of **Java Threads** in order to fetch information from various API calls in **pararell to reduce waiting times**.
> - The usage of `streams`, `maps`, `collectors` and `thread interaction`.
> - The very big importance of checking `null` values.
> - **Nested loops** and **big if/for** conditions.
> - Batched saving solution

> [!NOTE]
> The whole service is full of logs so that the developers can keep track of what is happening. This is done in purpose and will stay for debuging reasons and control reasons.
>
> There are planes in mind to add endpoints to sync one game or one achievement by the game apiId.

The only 3 functions of the service are so complex that we will require 3 separated sections to explain them in a way that makes sense:

### 3.5.1 Sync Player Games

It requires:
- `User`: A user object to know to who make the relation and from whom to fetch the games.

#### 3.5.1.1 Preparations

Before the sync service can perform it's duty, it needs to prepare the data for the logical steps:

**Fetch user games**
The service fetches a general list of all the games owned by the user using [`steamService.getPlayerGames()`](#244-get-user-games-by-steam-id-endpoint)

<details>
<summary>Code</summary>

```java
SteamUserGamesResponse response = steamService.getPlayersGames(user.getSteamId(), true, true, true, true, true);
```
</details>

**Save fetched user games id**
the returned user games ids are saved in a `Set<Integer>` structure named `responseGamesApiId` for later to fetch all the games in the **Database with matching Ids**.

<details>
<summary>Code</summary>

```java
Set<Integer> responseGamesApiId = response.getResponse().getGames()
                .stream()
                .map(SteamUserGamesResponse.Games::getAppid) // Transform each Game entity in response to its appid
                .collect(Collectors.toSet());
```
</details>

**Load games and userGames**
Using the `responseGamesApiId` and the `userId` we save all the `Games` and `UserGames` from the database in a `Map<Integer, Game/Usergame>` in order to have them already stored and get **easy and fast access** to data without straining the Database.

<details>
<summary>Code</summary>

```java
// Game Map example
Map<Integer, Game> existingGamesByApiId = gameRepository
                .findByApiIdIn(responseGamesApiId)
                .stream()
                .collect(Collectors.toMap(
                        Game::getApiId,
                        game -> game));
```
</details>

**Identify missing games**
After saving the data in `Map` structures we identify what games need to get fetched. Aka: See what `Games` in the response are missing in the `existingGamesByApiId` map. This will only be used for the details to avoid the API strain.

<details>
<summary>Code</summary>

```java
List<Integer> missingGamesIds = response.getResponse().getGames()
                .stream()
                .map(SteamUserGamesResponse.Games::getAppid)
                .filter(appId -> !existingGamesByApiId.containsKey(appId))
                .toList();
```
</details>

**Thread solution for API calling**
This is one of the most complicated to understand steps.

What we do here is, for each `missingGamesIds` we launch a new `task` inside a `thread` for it to complete independently of the main program flow.

We use the [`steamService.getGamesDetails()`](#242-get-game-details-endpoint) function to get each game's details. Each `task` is introduced in a `thread` thanks to our `steamSyncGamesApiCallExecutor`.

> [!NOTE]
> `steamSyncGamesApiCallExecutor` is a configured thread executor that takes the tasks and using its configuration launches threads to complete the tasks.

After completing all tasks, using a `.join()`, we save them in an `Array` that will later be transformed to another `Map<Integer, SteamGameDetailsResponse>` named `fetchedMap` that will improve performance for lookup.

<details>
<summary>Code</summary>

```java
List<CompletableFuture<Map.Entry<Integer, SteamGameDetailsResponse>>> futures = missingGamesIds
        .stream()
        .map(appId -> CompletableFuture
        .supplyAsync(() -> steamService.getGamesDetails(appId),
                steamSyncGamesApiCallExecutor)
        .exceptionally(ex -> {
            System.err.println("Error for appId " + appId + ": "
                    + ex.getMessage());
            return null;
        })
        .thenApply(details -> Map.entry(appId, details)))
        .toList();

// Wait for all futures to complete
CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

// Collect all futures results into a Map
Map<Integer, SteamGameDetailsResponse> fetchedMap = futures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
```
</details>

**Prepare sets**
Now, before we start the loop we create 2 Sets for `Game` and `UserGames` for future batch saving of all results.

**Main loop**
We loop for each game in the response using `response.getResponse.getGames()` and name each iteration `responseGame`.

<details>
<summary>Code</summary>

```java
for (SteamUserGamesResponse.Games responseGame : response.getResponse().getGames()) {...}
```
</details>

Then comes the logic part. We check for each game in the response if it exists in the database.

- If it exists, we skip it.
- If it doesent exists we create a new `Game` and `UserGame` and update its information using the response.

> [!NOTE]
> When a game already exists its `UserGame` relation with the user should be checked. This has been currently cut due to time restrictions and complexity.
>
> Otherwise, the code works perfectly fine as it should.

**Batch save**
Inside the loop, for each new `Game` and `UserGame` a new entry in the dedicated `Set` are created. Once the loop ends the Sets are updated.

<details>
<summary>Code</summary>

```java
// Insert entity in set - Inside the loop
newGames.add(Game);
newUserGames.add(userGame);

// Batch Save - Outside the loop
gameRepository.saveAll(newGames);
userGameRepository.saveAll(newUserGames);
```
</details>

> [!NOTE]
> This aproax avoids **DB strain** when saving each game individually on each interation.

### 3.5.2 Sync Player Achievements

It requires:
- `User`: A user object to know to who make the relation and from whom to fetch the games.

#### 3.5.2.1 Preparations

Before the sync service can perform it's duty, it needs to prepare the data for the logical steps:

**Check if user games needs sync**
It uses the existing `syncUserGames` function to check is the user games are already in sync.

<details>
<summary>Code</summary>

```java
if (userGameRepository.needsSteamSync(
        user.getUserId(),
        LocalDateTime.now().minusHours(2))) {
    syncPlayerGames(user);
}
```
</details>

**Fetch user games**
The service fetches all games of the user after the sync ends using [`userGameRepository.findByUserUserId()`]()

<details>
<summary>Code</summary>

```java
List<UserGames> userGames = userGameRepository.findByUserUserId(user.getUserId());

List<Integer> userGamesApiId = userGames
    .stream()
    .map(ug -> ug.getGame().getApiId())
    .toList();
```
</details>

**Fetch user achievements**
After that the service gets a hold of all the user's achievements using [`steamService.getPlayersAchievements`](#247-get-all-user-achievements-by-steam-id-endpoint)

It uses a list of all the Id from all the games that the user has.

<details>
<summary>Code</summary>

```java
 SteamAchievementsResponse achievementsResponse = steamService.getPlayersAchievements(user.getSteamId(), userGamesApiId);
```
</details>

**Save all the achievements of the response in Map**
When the service gets all the achievements in the big singular response it saves all the data in a big `Map<Integer, SteamAchievementsResponse.Games>` data structure, grouping all `Games` by their `apiId`.

Also, the recieved games that **contain** achievements are saved in a map for the futures.

<details>
<summary>Code</summary>

```java
Map<Integer, SteamAchievementsResponse.Games> achievementsResponseMap = achievementsResponse
        .getResponse().getGames()
        .stream()
        .filter(game -> game.getAchievements() != null)
        .collect(Collectors.toMap(
                SteamAchievementsResponse.Games::getAppid,
                g -> g));

// Save the games id of those games that actually have achievements
Set<Integer> gamesApiId = achievementsResponse.getResponse().getGames()
        .stream()
        .flatMap(responseGame -> responseGame.getAchievements() != null
        ? responseGame.getAchievements()
                .stream()
                .map(steamAchievement -> responseGame.getAppid())
        : Stream.empty())
        .collect(Collectors.toSet());
```
</details>

**Maping of existing data**
After all the data is collected the service maps all the information that is available in the database, equal as the `syncUserGames` did, for efficiency.

<details>
<summary>Code</summary>

```java
// Example of Mapping all existing Achievements from all games with achievements from the response
Map<String, Achievement> existingAchievements = achievementRepository
        .findByGameApiIdIn(gamesApiId)
        .stream()
        .collect(Collectors.toMap(
                a -> keyBuilder(
                        a.getGame().getApiId(),
                        a.getName()),
                a -> a,
                (a1, a2) -> a1));
```
</details>

**Identify games with missing achievements**
The backend checks all games if they need the sync in case the have missing achievements or information.

<details>
<summary>Code</summary>

```java
Set<Integer> missingGameApiIds = achievementsResponse.getResponse().getGames()
        .stream()
        .filter(game -> game.getAchievements() != null
        && game.getAchievements().stream()
                .anyMatch(ach -> !existingAchievements.containsKey(
                game.getAppid() + "-" + ach.getName())))
        .map(SteamAchievementsResponse.Games::getAppid)
        .collect(Collectors.toSet());
```
</details>

**Pararell Api Calls**
Same as in the `syncUserGames`, the individual Api calls are done by game, not achievement, and managed by individual `threads`. Here we use the `steamSyncAchievementsApiCallExecutor`.

After the calls are finished, they get joined and saved into a `Map<integer, SteamAchievementsDetailsResponse>`.

<details>
<summary>Code</summary>

```java
List<CompletableFuture<Map.Entry<Integer, SteamAchievementsDetailsResponse>>> futures = missingGameApiIds
        .stream()
        .map((gameApiId) -> CompletableFuture
        .supplyAsync(() -> steamService.getPlayerGamesAchievementsDetails(user.getSteamId(), gameApiId),
                steamSyncAchievementsApiCallExecutor)
        .exceptionally(ex -> {
            System.out.println("Error for achievement " + gameApiId + ": "
                    + ex);
            return null;
        })
        .thenApply(details -> Map.entry(gameApiId, details)))
        .toList();

// Wait for all futures to complete
CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

System.out.println("Futures completed");

// Collect all futures results into a Map
Map<Integer, SteamAchievementsDetailsResponse> achievementDetailsResponseMap = futures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
```
</details>

**Prepare Sets for batch insertion**
The same as `syncUserGames`, we prepare sets for batch inserts.

<details>
<summary>Code</summary>

```java
Set<Achievement> newAchievements = new HashSet<>();
Set<UserAchievements> newUserAchievements = new HashSet<>();
```
</details>

**Main Loop**
After all that, the app enters a loop where it checks for each game:
- If the game exists and is valid
- Get all achievements from the Map using the `Game.getApiId()`
- Loop for each achievement if data is null and use either the `detailData` or the `responseData`
- Create the relations in `UserAchievements`

> [!NOTE]
> When a game already exists its UserGame relation with the user should be checked. This has been currently cut due to time > restrictions and complexity.
>
> Otherwise, the code works perfectly fine as it should.

**Batch save**
Inside the loop, for each new `Achievement` and `AchievementGame` a new entry in the dedicated `Set` are created. Once the loop ends the Sets are updated.

<details>
<summary>Code</summary>

```java
// Insert entity in set - Inside the loop
newAchievements.add(Achievement);
newUserAchievements.add(userAchievement);

// Batch Save - Outside the loop
achievementRepository.saveAll(newAchievements);
achievementGameRepository.saveAll(newUserAchievements);
```

</details>

# 4. Config

Here is a brief explanation of the `/config` contents.

## 4.1 AppConfig

The `AppConfig` class is a **Spring Configuration class** responsible for defining application-level beans.

It is annotated with `@Configuration`, meaning Spring will scan it and register the defined beans inside the application context.

### Purpose

This class defines a `RestTemplate` bean.

<details>
<summary>Code</summary>

```java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

</details>

## 4.2 AsyncConfig

The `AsyncConfig` class is a **Spring Configuration class** responsible for enabling and configuring asynchronous execution in the application.

It is annotated with:

- `@Configuration` → Marks it as a configuration class managed by Spring.
- `@EnableAsync` → Enables Spring’s asynchronous method execution capability (`@Async`).

### Purpose

This configuration defines custom thread pools (Executors) used for handling asynchronous Steam API synchronization tasks.

Instead of running heavy API calls on the main application thread, the system delegates them to dedicated background threads.

**Configuration:**
- Core Pool Size: Is the base number of enabled threads
- Max Pool Size: Maximum dynamic number of threads
- Queue Capacity: The maximum number of tasks in each thread
- Thread Prefix: Name of the thread

### Why This Configuration Exists

- Prevents blocking HTTP requests while syncing large amounts of Steam data.
- Improves performance by executing API calls in parallel.
- Separates workloads (games vs achievements) into different thread pools.
- Provides better scalability and control over concurrency.

## 4.3 OpenApiConfig

The `OpenApiConfig` class is a **Spring Configuration class** responsible for configuring the OpenAPI (Swagger) documentation of the application.

It defines a custom `OpenAPI` bean that configures global API metadata and security settings.

### Purpose

This configuration:

- Defines global API information (title and version).
- Configures JWT authentication for the entire API.
- Enables the Swagger UI to support Bearer token authentication.

### Security Configuration

The class defines a security scheme named: **bearerAuth**

## 4.4 SteamProperties

The `SteamProperties` class is a **Spring configuration properties class** used to externalize and manage Steam-related configuration values.

It allows values defined in `application.properties` (or `application-secrets.properties`) to be mapped directly into a Java object.

### Annotations Explained

- `@Component`  
  Registers this class as a Spring Bean so it can be injected where needed.

- `@ConfigurationProperties(prefix = "steam")`  
  Binds all properties starting with `steam.*` to this class.

### Properties Mapped

This class maps the following configuration values:

<details>
<summary>Properties</summary>

```properties
steam.api-key=your_api_key
steam.api-url=https://api.steampowered.com
steam.store-url=https://store.steampowered.com
steam.test-user=123456789
```

</details>

<details>
<summary>Definition</summary>

```java
@RequiredArgsConstructor
@Service
public class SteamService {

    private final SteamProperties steamProperties;

}
```

</details>

# 5. Exceptions

In this section we talk about the exceptions created for this proyect.

## 5.1 GlobalExceptionHandler

GlobalExceptionHandler is a centralized exception handling class for the entire application.

It is annotated with:
- `@RestControllerAdvice` → Allows it to catch exceptions thrown by any controller globally.
- `@ExceptionHandler` → Specifies which exception each method handles.

### 5.1.1 What It Does

- Catches specific custom exceptions.
- Converts them into structured HTTP responses.
- Returns appropriate HTTP status codes.

> [!NOTE]
> **Possible responses**
>
> 404 (NOT_FOUND) → When a resource does not exist.
> 400 (BAD_REQUEST) → When the request is invalid (e.g., duplicate username/email).

Each handler:
- Creates an ErrorResponse
- Sets the correct HttpStatus
- Returns a ResponseEntity with that status

### 5.1.2 Why It Exists

- Avoids repetitive try-catch blocks in controllers.
- Ensures consistent error responses.
- Improves API clarity and maintainability.
- In short, it standardizes how errors are returned to API clients.

## 5.2 Exceptions

All the exceptions created are very self descriptive. They are called inside the services that need them and then they execute the HTTP response required.

## 6. Repositories
Los repositorios se usan todos para el acceso a la base de datos

### 6.1 Main Responsibilities

- Provide standard CRUD operations.
- Execute custom queries when needed.
- Handle entity relationships (User–Game–Achievement).
- Provide pagination and filtering.
- Provide counting and existence checks.
- Control sync validation queries with timestamps (e.g., checking if Steam sync is required).

### 6.2 Repositories used

- AchievementsRepository
- CategoryRepository
- GameRepository
- GenreRepository
- UserAchievementRepository
- UserGameRepository
- UserRepository

# 7. Simple backend flow

This section describes the backend flow executed when a user performs an action that requires data synchronization (e.g., fetching and syncing Steam games). Also will show a simple table of responsabilities and a conclusion of why it is done this way.

## 7.1 Flow

**Step 1 – Client Request (Frontend → Controller)**

The process begins when the frontend sends an HTTP request to the backend API.

Example: `GET /api/users/{id}/games`

The request is received by the corresponding **Controller** class.

The Controller:
- Validates the request parameters
- Extracts authentication data (JWT if required)
- Delegates the business logic to the Service layer

The controller does not contain business logic. Its responsibility is only request handling and routing.

**Step 2 – Service Layer Processing**

The Service layer contains the application’s business logic.

When the controller calls the service:

1. The service validates domain rules (e.g., user exists).
2. If required, it triggers a synchronization process.
3. It may call external APIs (e.g., Steam API).
4. It processes and transforms the received data.
5. It saves or updates entities in the database.

If synchronization involves heavy API calls, the process may be executed asynchronously using a configured Executor.

**Step 3 – External API Communication (If Required)**

If the requested data is not up-to-date:

- The service uses `RestTemplate`
- Sends a request to the Steam API
- Receives JSON data
- Maps it into DTOs or Entities
- Persists it using the Repository layer

Configuration values (API key, URLs) are obtained from `SteamProperties`.

**Step 4 – Repository Layer (Database Interaction)**

The Service communicates with the Repository layer to:

- Retrieve existing entities
- Save new data
- Update synchronized records

This layer abstracts database access using Spring Data JPA.

**Step 5 – Response to Client**

After processing:

- The Service returns the result to the Controller
- The Controller wraps it into a `ResponseEntity`
- A structured HTTP response is returned to the frontend

If any error occurs during the process:
- A custom exception is thrown
- `GlobalExceptionHandler` intercepts it
- A standardized error response is returned

---

## 7.2 Architectural Responsibilities Summary

| Layer        | Responsibility |
|--------------|---------------|
| Controller   | Handle HTTP request and response |
| Service      | Business logic and synchronization |
| Repository   | Database interaction |
| Config       | External configuration (API keys, async, OpenAPI) |
| ExceptionHandler | Centralized error handling |

---

## 7.3 Conclusion

This layered architecture ensures:

- Separation of concerns
- Clean maintainable code
- Centralized error handling
- Scalable synchronization processes
- Secure and configurable external API integration