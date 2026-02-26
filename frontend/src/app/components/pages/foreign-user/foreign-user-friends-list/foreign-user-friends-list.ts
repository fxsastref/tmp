import { Component, Input, signal } from '@angular/core';
import { User } from '../../../../models/user.model';
import { UserCard } from "../../../common/user-card/user-card";

@Component({
  selector: 'app-foreign-user-friends-list',
  imports: [UserCard],
  templateUrl: './foreign-user-friends-list.html',
  styleUrl: './foreign-user-friends-list.scss',
})
export class ForeignUserFriendsList {
  // Attributes
  @Input() user!: User | null;
  friends = signal<User[] | null>(null);
}
