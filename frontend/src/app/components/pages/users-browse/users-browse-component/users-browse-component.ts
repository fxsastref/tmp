import { Component, signal } from '@angular/core';
import { UsersBrowseUsersList } from "../users-browse-users-list/users-browse-users-list";
import { User } from '../../../../models/user.model';
import { UserService } from '../../../../services/user.service';
import { FormControl } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { UsersBrowseHero } from "../users-browse-hero/users-browse-hero";

@Component({
  selector: 'app-users-browse-component',
  imports: [UsersBrowseUsersList, UsersBrowseHero],
  templateUrl: './users-browse-component.html',
  styleUrl: './users-browse-component.scss',
})
export class UsersBrowseComponent { }
