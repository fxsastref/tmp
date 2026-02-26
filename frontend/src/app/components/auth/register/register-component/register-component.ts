import { Component } from '@angular/core';
import { RegisterForm } from '../register-form/register-form';

@Component({
  selector: 'app-register-component',
  imports: [RegisterForm],
  templateUrl: './register-component.html',
  styleUrl: './register-component.scss',
})
export class RegisterComponent {}
