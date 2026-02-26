import { Component, inject, signal } from '@angular/core';
import { RouterLink } from "@angular/router";
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-footer',
  imports: [RouterLink],
  templateUrl: './footer.html',
  styleUrl: './footer.scss',
})
export class Footer {
  // Injection to view the status of user and decide status of the Navbar
  authService = inject(AuthService);
}
