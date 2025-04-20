import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

// Create DTO to match the backend
export interface UserLoginDTO {
  userEmail: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  loginUrl = 'http://localhost:8081/login'; // must match the backend endpoint (quarkus)

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) {}

  // Function/method for user login
  userLogin(userLoginDTO: UserLoginDTO): Observable<any> {
    return this.httpClient.post(this.loginUrl, userLoginDTO);
  }

  // Store the toke, if login was successful
  storeToken(token: string): void {
    this.localStorageService.setItem('authToken', token);
  }

  // Retrieve the token
  getToken(): string {
    return this.localStorageService.getItem('authToken') || '';
  }

  // Remove the token
  removeToken(): void {
    return this.localStorageService.removeItem('authToken');
  }
}
