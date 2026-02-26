import { Routes } from '@angular/router';
import { RegisterComponent } from './components/auth/register/register-component/register-component';
import { LoginComponent } from './components/auth/login/login-component/login-component';
import { GameComponent } from './components/pages/game/game-component/game-component';
import { AccountComponent } from './components/pages/account/account-component/account-component';
import { authGuard } from './guards/auth.guard';
import { HomeComponent } from './components/pages/home/home-component/home-component';
import { ForeignUserComponent } from './components/pages/foreign-user/foreign-user-component/foreign-user-component';
import { GamesBrowseComponent } from './components/pages/games-browse/games-browse-component/games-browse-component';
import { UsersBrowseComponent } from './components/pages/users-browse/users-browse-component/users-browse-component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'games-browse', component: GamesBrowseComponent },
  { path: 'users-browse', component: UsersBrowseComponent },
  { path: 'account', component: AccountComponent, canActivate: [authGuard] },
  { path: 'account/:linked', component: AccountComponent, canActivate: [authGuard] },
  { path: 'foreign-user/:userId', component: ForeignUserComponent, canActivate: [authGuard] },
  { path: 'game-info/:gameId/:userId', component: GameComponent, canActivate: [authGuard] },
  { path: 'game-info/:gameId', component: GameComponent },
];
