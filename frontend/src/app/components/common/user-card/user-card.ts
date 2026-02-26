import { Component, Input } from '@angular/core';
import { User } from '../../../models/user.model';
import { Router, RouterLink } from "@angular/router";
import { MatIcon } from '@angular/material/icon';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-user-card',
  imports: [RouterLink, MatIcon],
  templateUrl: './user-card.html',
  styleUrl: './user-card.scss',
})
export class UserCard {
  // User received from parent component
  @Input() user!: User;

  // Constructor
  constructor(private router: Router, private authService: AuthService) { }

  // Methods
  // Redirects to the user page
  redirectToUserPage(): void {
    // if the user does not have their Steam account linked, it returns a message
    if (!this.user.steamId) {
      alert('The user does not have their Steam account linked.')
      return
    }
    
    const currentUser = this.authService.getCurrentUser()

    // If you are the user, you will be redirected to Account.
    if (this.user.steamId == currentUser?.steamId) {
      this.router.navigate(['/account'])
    }

    // If the user has a linked Steam account and it is not yours, they will be redirected to your page.
    if (this.user.steamId != currentUser?.steamId) {
      this.router.navigate(['/foreign-user', this.user.steamId])
    }
  }
}
