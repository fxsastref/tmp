# 🏆Platinum🏆
## 👥 Autors

**👮 Cristian Pérez Juárez**
- **Email**: cristianpj0808@gmail.com

**👷 Erik Vives Von Heyne**
- **Email**: erikvvh06@gmail.com

**👸 Luis Daniel Pozo Benz**
- **Email**: luispobe77@gmail.com

# 📑 Proyect description
Platinum is a web page dedicated to the management of the data retrieved from the Steam Api. Its main focus is on getting the **games** information, their **achievements** and the **users** that own them.

This way, each **user** is able to check for their **avhievement** progress with ease, without the need of entering Steam.

> [!IMPORTANT]
> ### Access the following links first in order to set up the application correctly. They also contain in-depth explanation of the functionalities of Platinum.

## 🌄 [Frontend readme](./frontend/README.md)
## 🌆 [Backend readme](./backend/README.md)
## 🐘 Postgres config
**In order to import the database, the following prerequisites will be needed**
- pgAdmin v9.11
- Create the database `platinum` 
    - username: `postgres`
    - password: `root`
- Right click on the Db->Restore the file
    - format: `plain`
    - select the file


# 🌱 Before logging in...
There are some pages an unauthenticated user can access(besides obviously the `login` and `register`):
- `home` page.
- `game` info (only the general version, as the one that includes achievements state requires a `steamid`).
- `games-browse`.
- `users-browse`.

# 🌼 Login to Platinum
In order to access the remaining Platinum pages, the user must login with  an existing `username` and corresponding `password`. 

A `successfull` login can be achieved by registering a new user first, but for the purpose of testing, we have provided three different test users with their credentials:


### **User Luis**

```json
    "username": "Luis",
    "password": "Luis12345"
```



### **User Cristian**

```json
    "username": "Cristian",
    "password": "Cristian12345"
```


### **User Erik**

```json
    "username": "Erik",
    "password": "Erik12345"
```


>[!NOTE]
>This will not only skip the registration process, but also help with testing the functionalities that require having a Steam account linked (as these users have their accounts already linked).

# 🌳 After logging in...
These three users are fully authorized and able to test every feature Platinum can offer:

### 👤🎮**See own games**
In the `account` page *(accessed by clicking on the users profile picture on the top right)*, there will be displayed a list of the user's owned games.

### 👤💎**See own achievements**
After clicking on a `game` card from the `account` page, the user will be redirected to the page of that `game`, from where he could click on the `achievements` tab and a list of the game's achievements will be displayed.

These achievements, unlike with the "not logged-in" variant, will display the state in which the user has it (either `Unlocked` or `Not unlocked`).

>[!WARNING]
>This is the only way of seeing the user's own achievements. Accessing through the home page, clicking on a game card and then on the achievements tab, will only provide general information of the achievements.

# 🥀 If logged in with a new user...
Initially, the user will not have any owned game or achievement data, as originally he lacks a `steamId` to access in order to get this information. 

To link a Steam account, the user first needs to have a Steam account and then click on the `link your Steam Account` button found in the top right of the `account` page.

> [!CAUTION]
> After returning from the Steam login page, a single **page reload** will be necessary in order to load the user's Steam information.