import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { jwtDecode } from 'jwt-decode';

// Create DTO to match the backend
export interface UserLoginDTO {
  userEmail: string;
  password: string;
}

export interface DecodedToken {
  sub: string;
  role: string[] | string;
  exp: number;
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

  // Decode the token for RBAC
  getUserRoles(): string[] {
    const token = this.getToken();
    if (!token) return [];

    try {
      const decoded: any = jwtDecode(token);
      // Adjust this line depending on how the roles are stored in your JWT
      const roles = decoded.groups || decoded.role || [];
      console.log('Decoded token:', decoded);
      return Array.isArray(roles) ? roles : [roles];
    } catch (error) {
      console.error('Invalid token', error);
      return [];
    }
  }
  // Checking Role
  isRole(role: string): boolean {
    return this.getUserRoles().includes(role);
  }
}
