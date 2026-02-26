import { Component, Input, signal } from '@angular/core';
import { UserCard } from "../../../common/user-card/user-card";
import { User } from '../../../../models/user.model';

@Component({
  selector: 'app-account-friends-list',
  imports: [UserCard],
  templateUrl: './account-friends-list.html',
  styleUrl: './account-friends-list.scss',
})
export class AccountFriendsList {
  // Attributes
  @Input() user!: User | null;
  friends = signal<User[] | null>(null);
}
