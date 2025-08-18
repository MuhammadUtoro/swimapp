import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Create an interface to match the backend
export interface UserRegistrationDTO {
  lastName: string;
  firstName: string;
  userEmail: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  registerUrl = '192.168.0.175:8081/register';

  // Constructor for HttpClient
  constructor(private httpClient: HttpClient) {}

  // Function to user registration
  userRegister(
    userRegistrationDTO: UserRegistrationDTO
  ): Observable<UserRegistrationDTO> {
    return this.httpClient.post<UserRegistrationDTO>(
      this.registerUrl,
      userRegistrationDTO
    );
  }
}
