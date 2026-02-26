import { Component, Input } from '@angular/core';
import { MatTab, MatTabGroup } from '@angular/material/tabs';
import { AccountFriendsList } from "../account-friends-list/account-friends-list";
import { AccountGamesList } from '../account-games-list/account-games-list';
import { User } from '../../../../models/user.model';
import { Game } from '../../../../models/game.model';

@Component({
  selector: 'app-account-tabs',
  imports: [MatTabGroup, MatTab, AccountGamesList, AccountFriendsList],
  templateUrl: './account-tabs.html',
  styleUrl: './account-tabs.scss',
})
export class AccountTabs {
  // Attributes
  @Input() user!: User | null;
  @Input() games!: Game[] | null;
  @Input() loading = false;
}
