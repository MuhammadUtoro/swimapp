import { Component } from '@angular/core';
import {
  FormControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {
  RegistrationService,
  UserRegistrationDTO,
} from '../../services/registration.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { error } from 'console';

@Component({
  selector: 'app-registration',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss',
})
export class RegistrationComponent {
  registrationForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private registrationService: RegistrationService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.registrationForm = this.formBuilder.group({
      lastName: ['', Validators.required],
      firstName: ['', Validators.required],
      userEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  // Function to submit the form
  submit(): void {
    if (this.registrationForm.invalid) {
      this.snackBar.open('Please complete the form', 'Close', {
        duration: 1500,
      });
      return;
    }
    const userRegistrationDTO: UserRegistrationDTO =
      this.registrationForm.value;
    this.registrationService.userRegister(userRegistrationDTO).subscribe({
      next: (res) => {
        this.snackBar.open('Registration success!', 'Close', {
          duration: 1500,
        });
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.snackBar.open('Registration failed!', 'Close', {
          duration: 1500,
        });
      },
    });
  }
}
