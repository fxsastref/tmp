import { Component, Input, OnInit, signal } from '@angular/core';
import { User } from '../../../../models/user.model';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { Game } from '../../../../models/game.model';
import { AuthService } from '../../../../services/auth.service';
import { UsersStoreService } from '../../../../services/stores/users-store.service';
import { UserService } from '../../../../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-account-banner',
  imports: [MatIconModule, RouterLink],
  templateUrl: './account-banner.html',
  styleUrl: './account-banner.scss',
})
export class AccountBanner implements OnInit {
  // Attributes
  loading = signal<boolean>(false);
  error = signal<HttpErrorResponse | null>(null);

  @Input() user!: User | null;
  @Input() games!: Game[] | null;

  totalGames = signal<number | null>(null);
  totalAchievements = signal<number | null>(null);

  // Constructor
  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute, private userService: UserService) { }

  // OnInit method
  ngOnInit(): void {
    this.tryLinkSteamAccount();
    this.countOwnAchievements();
    this.countOwnGames();
  }

  // Methods
  // Try to link Steam account
  tryLinkSteamAccount(): void {
    const steamId = this.getSteamId();
    if (steamId) this.linkSteamAccount(steamId);
  }

  // Get Steam ID
  getSteamId(): string | void {
    // Get the steamId from the URL when returning from the steam login
    const claimedId = this.route.snapshot.queryParamMap.get('openid.claimed_id');
    if (claimedId) {
      return claimedId.slice(37);
    }
  }

  // Link the Steam account to the user
  linkSteamAccount(id: string) {
    this.loading.set(true);

    // Service call
    this.authService.linkSteamAccount(id).subscribe({
      next: () => {
        this.router.navigate(['/home']);
        this.loading.set(false);
        this.clearCache();
      },

      error: err => console.error('Link error: ', err)
    });
  }

  // Redirect to the Steam login official page
  redirectToSteam(): void {
    if (confirm("Are you sure? This will redirect you to the Steam login website")) {
      const loginUrlParams =
        'https://steamcommunity.com/openid/login?' +
        'openid.ns=' + 'http://specs.openid.net/auth/2.0&' +
        'openid.mode=' + 'checkid_setup&' +
        'openid.return_to=' + 'http://localhost:4200/account&' +
        'openid.realm=' + 'http://localhost:4200&' +
        'openid.identity=' + 'http://specs.openid.net/auth/2.0/identifier_select&' +
        'openid.claimed_id=' + 'http://specs.openid.net/auth/2.0/identifier_select';

      window.location.href = loginUrlParams;
    }
  }

  // Get the total games
  countOwnGames(): void {
   this.userService.countOwnUserGames().subscribe({
    next: (data) => {
      this.totalGames.set(data);
    },

    error: (err) => {
      this.error.set(err);
      console.log(err);
    }
   })
  }

  // Get the total achievements
  countOwnAchievements(): void {
   this.userService.countOwnUserAchievements().subscribe({
    next: (data) => {
      this.totalAchievements.set(data);
    },

    error: (err) => {
      this.error.set(err);
      console.log(err);
    }
   })
  }

  clearCache(): void {
    // Save token
    const authToken = localStorage.getItem('auth_token');

    // Clear cache and localStorage
    localStorage.clear();

    // Restore token
    if (authToken !== null) {
      localStorage.setItem('auth_token', authToken);
    }
  }
}