import { Component, Input } from '@angular/core';
import { MatTab, MatTabGroup } from '@angular/material/tabs';
import { ForeignUserGamesList } from "../foreign-user-games-list/foreign-user-games-list";
import { ForeignUserFriendsList } from "../foreign-user-friends-list/foreign-user-friends-list";
import { User } from '../../../../models/user.model';
import { Game } from '../../../../models/game.model';

@Component({
  selector: 'app-foreign-user-tabs',
  imports: [MatTabGroup, MatTab, ForeignUserGamesList, ForeignUserFriendsList],
  templateUrl: './foreign-user-tabs.html',
  styleUrl: './foreign-user-tabs.scss',
})
export class ForeignUserTabs {
  // Attributes
  @Input() user!: User | null;
  @Input() games!: Game[] | null;
}
