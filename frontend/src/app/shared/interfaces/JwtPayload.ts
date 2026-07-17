export interface JwtPayload {

  sub: string;

  username: string;

  authorities: string[];

  exp: number;

  iat: number;

}