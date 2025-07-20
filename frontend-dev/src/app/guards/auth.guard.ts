import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  const expectedRoles = route.data['roles'] as string | undefined;
  const token = loginService.getToken();

  if (!token) {
    router.navigate(['login']);
    return false;
  }

  if (expectedRoles && expectedRoles.length > 0) {
    const userRoles = loginService.getUserRoles();
    const hasRole = userRoles.some((role) =>
      expectedRoles.includes(role.toLowerCase())
    );

    if (!hasRole) {
      router.navigate(['unauthorized']);
      return false;
    }
  }
  return true;
};
