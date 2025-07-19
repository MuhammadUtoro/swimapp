import { Component, inject } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatGridListModule } from '@angular/material/grid-list';
import { AsyncPipe, CommonModule } from '@angular/common';
import { map } from 'rxjs/operators';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatCardModule,
    MatIconModule,
    RouterModule,
    MatButtonModule,
    RouterLink,
    AsyncPipe,
    MatGridListModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  userRole: string | null = null;
  private breakPointObserver = inject(BreakpointObserver);

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.setUserRole();
    }, 0); // Helps ensure token is ready after login
  }

  setUserRole(): void {
    const roles = this.loginService.getUserRoles();
    this.userRole = roles.length > 0 ? roles[0] : null;
    console.log('User role:', this.userRole);
  }

  cards = this.breakPointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Card 1', cols: 1, rows: 1 },
          { title: 'Card 2', cols: 1, rows: 1 },
          { title: 'Card 3', cols: 1, rows: 1 },
          { title: 'Card 4', cols: 1, rows: 1 },
        ];
      }

      return [
        { title: 'Card 1', cols: 1, rows: 1 },
        { title: 'Card 2', cols: 1, rows: 2 },
        { title: 'Card 3', cols: 1, rows: 2 },
        { title: 'Card 4', cols: 1, rows: 1 },
      ];
    })
  );
}
