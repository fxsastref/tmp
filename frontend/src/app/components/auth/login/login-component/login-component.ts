import { Component } from '@angular/core';
import { LoginForm } from '../login-form/login-form';

@Component({
  selector: 'app-login-component',
  imports: [LoginForm],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent { }
