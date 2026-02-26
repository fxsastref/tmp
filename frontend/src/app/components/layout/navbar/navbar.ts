import { Component, inject, OnInit, signal } from '@angular/core';
import { User } from '../../../models/user.model';
import { AuthService } from '../../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from "@angular/router";
import { MatIcon } from '@angular/material/icon';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, MatIcon],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar implements OnInit {
  // Attributes
  user = signal<User | null>(null);
  error = signal<HttpErrorResponse | null>(null);
  isUserMenuOpen = false;
  isMobileMenuOpen = false;
  
  // Constructor
  constructor(private userService: UserService, private authService: AuthService) { }
  
  // OnInit method
  ngOnInit(): void {
    this.loadUser();
    this.getOwnSummary();
  }
      
  // Methods
  // Load current user logged
  loadUser(): void {
    this.error.set(null);

    this.authService.currentUser$.subscribe({
      next: (data) => {
        this.user.set(data);
      },

      error: (err) => {
        this.error.set(err);
      }
    })
  }

  // Update user with Steam info
  getOwnSummary(): void {
    this.error.set(null);

    this.userService.getOwnSummary().subscribe({
      error: (err) => {
        this.error.set(err);
      }
    })
  }

  toggleUserMenu(): void {
    this.isUserMenuOpen = !this.isUserMenuOpen;
  }

  closeUserMenu(): void {
    this.isUserMenuOpen = false;
  }

  toggleMobileMenu(): void {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
  }

  logout(): void {
    this.authService.logout();
  }
}
