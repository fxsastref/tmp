import { Component, Input, OnInit, signal } from '@angular/core';
import { User } from '../../../../models/user.model';
import { UserCard } from "../../../common/user-card/user-card";
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../../../services/user.service';
import { catchError, debounceTime, distinctUntilChanged, of, switchMap, tap } from 'rxjs';

@Component({
  selector: 'app-users-browse-users-list',
  imports: [UserCard, ReactiveFormsModule],
  templateUrl: './users-browse-users-list.html',
  styleUrl: './users-browse-users-list.scss',
})
export class UsersBrowseUsersList implements OnInit {
  // Attributes
  usersList = signal<User[]>([]);
  searchText = new FormControl('');
  error = signal<HttpErrorResponse | null>(null);
  loading = signal<boolean>(false);

  // Constructor
  constructor(private userService: UserService) { }

  // On Init method
  ngOnInit(): void {
    this.searchUser();
  }

  // Methods
  // Search users by triad
  searchUser(): void {
    this.searchText.valueChanges.pipe(
      debounceTime(400), // wait 400ms after writing
      distinctUntilChanged(), // avoid repeating the same search

      // Initial values
      tap(() => {
        this.loading.set(true);
        this.error.set(null);
      }),

      // Cancel previous requests and make the current request
      switchMap(value => {
        if (!value) {
          this.usersList.set([]);
          return of([]);
        }

        return this.userService.searchUsers(value).pipe(
          catchError(err => {
            this.error.set(err);
            return of([]);
          })
        );
      })
    ).subscribe(users => {
      this.usersList.set(users);
      this.loading.set(false);
    });
  }
}
