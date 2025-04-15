import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Create DTO to match the backend
export interface userLoginDTO {
  userEmail: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  loginUrl = 'http://localhost:8081/login'; // must match the backend endpoint (quarkus)

  constructor(private httpClient: HttpClient) {}

  // Function/method for user login
  userLogin(userLoginDTO: userLoginDTO): Observable<any> {
    return this.httpClient.post(this.loginUrl, userLoginDTO);
  }

  // Store the toke, if login was successful
  storeToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  // Retrieve the token
  getToken(): string {
    return localStorage.getItem('authToken') || '';
  }

  // Remove the token
  removeToken(): void {
    return localStorage.removeItem('authToken');
  }
}
