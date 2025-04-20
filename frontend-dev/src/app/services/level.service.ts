import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

// Create DTO to match the backend
export interface LevelDTO {
  levelName: string;
  requirements: string[];
}

@Injectable({
  providedIn: 'root',
})
export class LevelService {
  levelUrl = 'http://localhost:8081/levels';

  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  // Function to get token
  private getToken(): string {
    return this.loginService.getToken();
  }

  // Function to retrieve all Levels
  getAllLevels(): Observable<LevelDTO[]> {
    const token = this.loginService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get<LevelDTO[]>(this.levelUrl, { headers });
  }

  // Function to add level
  addLevel(levelDTO: LevelDTO): Observable<LevelDTO[]> {
    const token = this.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.post<LevelDTO[]>(this.levelUrl, levelDTO, {
      headers,
    });
  }
}
