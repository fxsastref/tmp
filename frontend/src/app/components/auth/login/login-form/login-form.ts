import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../../services/auth.service';
import { LoginRequest } from '../../../../models/user.model';
import { Router, RouterLink } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm implements OnInit {
  // Attributes
  loading = signal(false);
  error = signal<HttpErrorResponse | null>(null);
  loginForm!: FormGroup;

  // Constructor
  constructor(private authService: AuthService, private router: Router, private snackBar: MatSnackBar) { }

  // OnInit method  
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(32)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(64)])
    })
  }

  // Methods
  onSubmit(): void {
    this.error.set(null)
    this.loading.set(true)

    // Create request
    const request: LoginRequest = { username: this.loginForm.value.username, password: this.loginForm.value.password }

    // Service Call
    this.authService.login(request).subscribe({
      next: () => {
        this.router.navigate(['/home']);
        this.loading.set(false);
      },
      error: (err) => {
        this.snackBar.open('User not found', 'OK', {
          duration: 3000,
          verticalPosition: 'bottom',
          horizontalPosition: 'right',
          panelClass: ['error-snackbar']
        })
        this.error.set(err);
        this.loading.set(false);
      }
    })
  }

  reload(): void {
    window.location.reload()
  }
}
