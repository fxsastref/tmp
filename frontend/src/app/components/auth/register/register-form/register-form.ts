import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../../services/auth.service';
import { RegisterRequest } from '../../../../models/user.model';
import { Router, RouterLink } from "@angular/router";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm implements OnInit {
  // Attributes
  loading = signal(false);
  error = signal<HttpErrorResponse | null>(null);
  registerForm!: FormGroup;

  // Constructor
  constructor(private router: Router, private authService: AuthService, private snackBar: MatSnackBar) { }

  // OnInit method
  ngOnInit(): void {
    this.registerForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(32)]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(64)]),
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  // Methods
  onSubmit(): void {
    this.error.set(null);
    this.loading.set(true);

    // Create request
    const request: RegisterRequest = { username: this.registerForm.value.username, password: this.registerForm.value.password, email: this.registerForm.value.email }

    // Service Call
    this.authService.register(request).subscribe({
      next: (data) => {
        this.snackBar.open(data.message, 'OK', {
          duration: 3000,
          verticalPosition: 'bottom',
          horizontalPosition: 'right',
          panelClass: ['success-snackbar']
        })
        this.registerForm.reset();
        this.router.navigate(["/login"])
        this.loading.set(false);
      },
      error: (err) => {
        this.snackBar.open(err.message, 'OK', {
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
