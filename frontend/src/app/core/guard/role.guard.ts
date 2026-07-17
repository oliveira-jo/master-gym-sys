import { CanActivateFn, Router } from "@angular/router";
import { AuthService } from "../service/auth.service";
import { inject } from "@angular/core";

export const roleGuard: CanActivateFn = (route) => {

  const auth = inject(AuthService);

  const router = inject(Router);

  const roles = route.data['roles'] as string[];

  if (auth.hasAnyRole(roles)) {
    return true;
  }

  return router.createUrlTree(['/']);
};
