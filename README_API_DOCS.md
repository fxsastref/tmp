# Platinum API Endpoints

Here are all the functional enpoints listed with the Base URL, the required arguments, the response.

## Login

**Endpoint:** `GET /auth/login`  
**Base URL:** `http://localhost:8080`

### Headers

| Header | Value | Description |
| ------ | ----- | ----------- |
| `None` |       |             |

### Arguments

| Argument   | Type   | Description            | Required |
| ---------- | ------ | ---------------------- | -------- |
| `username` | string | User's unique username | **Yes**  |
| `password` | string | User's unique password | **Yes**  |

### Example IWR Request

```powershell
iwr -Method POST -Uri http://localhost:8080/auth/login -ContentType "application/json" -Body '{"username":"testuser","password":"secret123"}'
```

### Returns

| Argument   | Type   | Description           |
| ---------- | ------ | --------------------- |
| `username` | string | User's username       |
| `email`    | string | User's personal email |
| `role`     | string | User's role           |
| `token`    | string | Session token         |
| `type`     | string | Type of token         |

```json
{
  "email": "testuser@example.com",
  "role": "USER",
  "token": "token_here",
  "type": "BEARER",
  "username": "testuser"
}
```

### Error

On error the enpoint returns code **400 - Bad Request**

---

## Register

**Endpoint:** `GET /auth/register`  
**Base URL:** `http://localhost:8080`

### Headers

| Header | Value | Description |
| ------ | ----- | ----------- |
| `None` |       |             |

### Arguments

| Argument   | Type   | Description            | Required |
| ---------- | ------ | ---------------------- | -------- |
| `username` | string | User's unique username | **Yes**  |
| `password` | string | User's unique password | **Yes**  |
| `email`    | string | User's unique email    | **Yes**  |

### Example IWR Request

```powershell
iwr -Method POST -Uri http://localhost:8080/auth/register -ContentType "application/json" -Body '{"username":"testuser","password":"secret123","email":"testuser@example.com"}'
```

### Returns

| Argument  | Type   | Description                     |
| --------- | ------ | ------------------------------- |
| `message` | string | A message confirming the action |

```json
{
  "message": "User registered successfully"
}
```

### Error

On error the enpoint returns code **400 - Bad Request**

---

## Get Games

**Endpoint:** `GET /steam/games`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Arguments

| Argument | Type | Description | Required |
| -------- | ---- | ----------- | -------- |
| `none`   |      |             |          |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/games"
```

> [!NOTE]
> The actual endpoint returns up to **23000** game entries.

### Returns

| Argument                | Type    | Description                   |
| ----------------------- | ------- | ----------------------------- |
| `response`              | object  | Root object of the response   |
| `response.apps`         | array   | A list of app objects (Games) |
| `response.apps[].appid` | integer | Unique app Id                 |
| `response.apps[].name`  | string  | App id                        |

```json
{
  "response": {
    "apps": [
      {"appid": 10, "name": "Counter-Strike"},
      {"appid": 20, "name": "Team Fortress 2"},
      {...}
    ]
  }
}
```

---

## Get Player Summary

**Endpoint:** `GET /steam/players/*steamId*/summary`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Path Parameters

| Parameter | Type   | Description                   | Required |
| --------- | ------ | ----------------------------- | -------- |
| `steamId` | string | Unique Steam id from the user | **Yes**  |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/players/76561199024404784/summary"
```

### Returns

| Argument                                    | Type    | Description                                    |
| ------------------------------------------- | ------- | ---------------------------------------------- |
| `response`                                  | object  | Root object of the response                    |
| `response.players`                          | list    | List of Steam player profiles                  |
| `response.players.steamId`                  | string  | The user's 64-bit Steam ID                     |
| `response.players.personaname`              | string  | The user's Steam display name                  |
| `response.players.profileurl`               | string  | URL to the user's Steam profile                |
| `response.players.avatar`                   | string  | URL to the user's avatar image (small)         |
| `response.players.avatarmedium`             | string  | URL to the user's avatar image (medium)        |
| `response.players.avatarfull`               | string  | URL to the user's avatar image (full)          |
| `response.players.avatarhash`               | string  | Hash of the user's avatar image                |
| `response.players.communityvisibilitystate` | integer | Profile visibility state (1=private, 3=public) |
| `response.players.profilestate`             | integer | Profile setup status                           |
| `response.players.personastate`             | integer | Online status (0=offline, 1=online, etc.)      |
| `response.players.personastateflags`        | integer | Persona state flags                            |
| `response.players.lastlogoff`               | integer | Last logoff time (Unix timestamp)              |
| `response.players.timecreated`              | integer | Account creation time (Unix timestamp)         |
| `response.players.primaryclanid`            | string  | Steam ID of the user's primary clan            |
| `response.players.loccountrycode`           | string  | Country code set on the user's profile         |

```json
{
  "response": {
    "players": [
      {
        "avatar": "https://avatars.steamstatic.com/f7214e4f19372c8ec33c3906c4335b8421062c91.jpg",
        "avatarfull": "https://avatars.steamstatic.com/f7214e4f19372c8ec33c3906c4335b8421062c91_full.jpg",
        "avatarhash": "f7214e4f19372c8ec33c3906c4335b8421062c91",
        "avatarmedium": "https://avatars.steamstatic.com/f7214e4f19372c8ec33c3906c4335b8421062c91_medium.jpg",
        "communityvisibilitystate": 3,
        "lastlogoff": 1769948322,
        "loccountrycode": "ES",
        "personaname": "ElHeyne",
        "personastate": 1,
        "personastateflags": 0,
        "primaryclanid": "103582791429521408",
        "profilestate": 1,
        "profileurl": "https://steamcommunity.com/profiles/76561199024404784/",
        "steamId": "76561199024404784",
        "timecreated": 1580806289
      }
    ]
  }
}
```

---

## Get Player Games

**Endpoint:** `GET /steam/players/*steamId*/games`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Arguments

| Argument                 | Type    | Description                                                                              | Required |
| ------------------------ | ------- | ---------------------------------------------------------------------------------------- | -------- |
| `includeAppInfo`         | boolean | If `true`, include additional app info (name, icon URL, etc.)                            | **No**   |
| `includePlayedFreeGames` | boolean | If `true`, include free games that the player has played                                 | **No**   |
| `includeFreeSub`         | boolean | If `true`, include games from free subscriptions                                         | **No**   |
| `skipUnvettedApps`       | boolean | If `true`, skips apps that are unvetted / not fully released on Steam                    | **No**   |
| `includeExtendedInfo`    | boolean | If `true`, include extended information for each game (platform-specific playtime, etc.) | **No**   |

### Path Parameters

| Parameter | Type   | Description                   | Required |
| --------- | ------ | ----------------------------- | -------- |
| `steamId` | string | Unique Steam id from the user | **Yes**  |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/players/76561199024404784/games"
```

### Returns

| Argument                                     | Type    | Description                                    |
| -------------------------------------------- | ------- | ---------------------------------------------- |
| `response`                                   | object  | Root object of the response                    |
| `response.game_count`                        | integer | Total number of games owned by the player      |
| `response.games`                             | list    | List of game objects owned by the player       |
| `response.games.appid`                       | integer | Steam AppID of the game                        |
| `response.games.name`                        | string  | Name of the game                               |
| `response.games.playtime_forever`            | integer | Total playtime in minutes across all platforms |
| `response.games.img_icon_url`                | string  | URL hash for the game's icon image             |
| `response.games.has_community_visible_stats` | boolean | Whether the game has public stats available    |
| `response.games.playtime_windows_forever`    | integer | Total playtime on Windows in minutes           |
| `response.games.playtime_mac_forever`        | integer | Total playtime on Mac in minutes               |
| `response.games.playtime_linux_forever`      | integer | Total playtime on Linux in minutes             |
| `response.games.playtime_deck_forever`       | integer | Total playtime on Steam Deck in minutes        |
| `response.games.rtime_last_played`           | long    | Last played time as a Unix timestamp           |
| `response.games.playtime_disconnected`       | integer | Playtime while disconnected or offline         |

```json
{
  "response": {
    "game_count": 168,
    "games": [
      {
        "appid": 400,
        "name": "Portal",
        "playtime_forever": 155,
        "img_icon_url": "cfa928ab4119dd137e50d728e8fe703e4e970aff",
        "has_community_visible_stats": true,
        "playtime_windows_forever": 155,
        "playtime_mac_forever": 0,
        "playtime_linux_forever": 0,
        "playtime_deck_forever": 0,
        "rtime_last_played": 1692052556,
        "playtime_disconnected": 0
      },
      {...}
    ]
  }
}
```

---

## Get Player Game Recent

**Endpoint:** `GET /steam/players/*steamId*/games/recent`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Arguments

| Argument | Type    | Description                               | Required |
| -------- | ------- | ----------------------------------------- | -------- |
| `count`  | integer | Size of the returned list of recent games | **No**   |

### Path Parameters

| Parameter | Type   | Description                   | Required |
| --------- | ------ | ----------------------------- | -------- |
| `steamId` | string | Unique Steam id from the user | **Yes**  |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/players/76561199024404784/games/recent"
```

### Returns

| Argument                          | Type    | Description                                               |
| --------------------------------- | ------- | --------------------------------------------------------- |
| `response`                        | object  | Root object of the response                               |
| `response.total_count`            | integer | Total number of games recently played by the player       |
| `response.games`                  | list    | List of game objects owned by the player                  |
| `response.games.appid`            | integer | Steam AppID of the game                                   |
| `response.games.name`             | string  | Name of the game                                          |
| `response.games.playtime_2weeks`  | integer | Total playtime in minutes across all platforms in 2 weeks |
| `response.games.playtime_forever` | integer | Total playtime in minutes across all platforms            |
| `response.games.img_icon_url`     | string  | URL hash for the game's icon image                        |

```json
{
  "response": {
    "total_count": 8,
    "games": [
      {
        "appid": 1342330,
        "name": "Mad Games Tycoon 2",
        "playtime_2weeks": 305,
        "playtime_forever": 23701,
        "img_icon_url": "5d6b0970f00836c98988817b7e09f529aa5187b0"
      },
      {...}
    ]
  }
}
```

---

## Get Player Game Achievements

**Endpoint:** `GET /steam/players/*steamId*/games/*appid*/achievements`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Path Parameters

| Parameters | Type   | Description                                           | Required |
| ---------- | ------ | ----------------------------------------------------- | -------- |
| `steamId`  | string | Unique Steam id from the user                         | **Yes**  |
| `appid`    | string | Unique Steam id from the app to get the Archievements | **Yes**  |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/players/76561199024404784/games/1574820/achievements"
```

> [!NOTE]
> The endpoint, now, returns the achievements in the game's language.

### Returns

| Argument                              | Type    | Description                                    |
| ------------------------------------- | ------- | ---------------------------------------------- |
| `playerstats`                         | object  | Root object of the response                    |
| `playerstats.steamID`                 | string  | The user's Steam ID                            |
| `playerstats.gameName`                | string  | The selected game                              |
| `playerstats.success`                 | boolean | Request status                                 |
| `playerstats.achievements`            | list    | Achievements listed                            |
| `playerstats.achievements.apiname`    | string  | Achievement name                               |
| `playerstats.achievements.achieved`   | integer | Achievement status 1=achieved / 0=not achieved |
| `playerstats.achievements.unlocktime` | integer | Achievement unlock time in a timestamp format  |

```json
{
  "playerstats": {
    "steamID": "76561199024404784",
    "gameName": "Until Then",
    "achievements": [
      {
        "apiname": "ACH_MANIA_AUDITION_100",
        "achieved": 0,
        "unlocktime": 0
      },
      {
        "apiname": "ACH_BIKE_WIN",
        "achieved": 1,
        "unlocktime": 1768569596
      },
      {...}
    ],
    "success": true
  }
}
```

---

## Get Player Achievements

**Endpoint:** `GET /steam/players/*steamId*/achievements`  
**Base URL:** `http://localhost:8080`

### Headers

| Header        | Value                    | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| Authorization | Bearer `your_token_here` | Access token for authentication |

### Arguments

| Argument | Type              | Description                             | Required |
| -------- | ----------------- | --------------------------------------- | -------- |
| `appids` | integer / various | Steam app to retrieve achievements from | **Yes**  |

### Path Parameters

| Parameters | Type   | Description                   | Required |
| ---------- | ------ | ----------------------------- | -------- |
| `steamId`  | string | Unique Steam id from the user | **Yes**  |

### Example IWR Request

```powershell
iwr -Headers @{ "Authorization" = "Bearer your_token_here" } -Method GET -Uri "http://localhost:8080/steam/players/76561199024404784/achievements?appids=1342330&appids=949230"
```

> [!NOTE]
> The endpoint, now, returns the achievements in the game's language.

### Returns

| Argument                                              | Type    | Description                                        |
| ----------------------------------------------------- | ------- | -------------------------------------------------- |
| `response`                                            | object  | Root object of the response                        |
| `response.games`                                      | list    | List of Steam games returned                       |
| `response.games.appid`                                | integer | Steam application ID                               |
| `response.games.total_achievements`                   | integer | Total number of achievements in the game           |
| `response.games.achievements`                         | list    | List of achievements for the game                  |
| `response.games.achievements.statid`                  | integer | Internal statistic ID                              |
| `response.games.achievements.bit`                     | integer | Bit index representing the achievement             |
| `response.games.achievements.name`                    | string  | Achievement API name                               |
| `response.games.achievements.desc`                    | string  | Achievement description                            |
| `response.games.achievements.icon`                    | string  | URL of the unlocked achievement icon               |
| `response.games.achievements.icon_gray`               | string  | URL of the locked achievement icon                 |
| `response.games.achievements.hidden`                  | boolean | Whether the achievement is hidden                  |
| `response.games.achievements.player_percent_unlocked` | number  | Percentage of players who unlocked the achievement |

```json
{
  "response": {
    "games": [
      {
        "appid": 1342330,
        "total_achievements": 73,
        "achievements": [
          {
            "statid": 1,
            "bit": 6,
            "name": "Super Lario World",
            "desc": "Create an platformer with a rating of at least 70%.",
            "icon": "27b8689b12105d9fafbee6c14067ab33f7fa51f6.jpg",
            "icon_gray": "0172bba081531c548774003d1efca16d312b991c.jpg",
            "hidden": false,
            "player_percent_unlocked": "26.8"
          },
          {
            "statid": 3,
            "bit": 7,
            "name": "Shopping Tour II",
            "desc": "Buy at least 10 NPC developers or publishers.",
            "icon": "85243cda71c1ac2975e6a3daeab34dce0b8a92dd.jpg",
            "icon_gray": "3bf73a0bec5b60142fba384bab38fe2d46cdeefd.jpg",
            "hidden": false,
            "player_percent_unlocked": "15.1"
          },
          {...}
        ]
      }
    ]
  }
}
```
