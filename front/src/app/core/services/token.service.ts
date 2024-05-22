import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { constants } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  isAuthentication:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  //local storage ne fonctionne pas parce que SSR

  constructor() {
    const token = this.getToken();
    if(token) {
      this.updateToken(true);
    }
  }

  updateToken(status: boolean) {
    this.isAuthentication.next(status);
  }

  getToken():string | null {
    return localStorage.getItem(constants.CURRENT_TOKEN)
  }

  setToken(token: string) {
    this.updateToken(true);
    localStorage.setItem(constants.CURRENT_TOKEN, token)
  }

  removeToken() {
    this.updateToken(false);
    localStorage.removeItem(constants.CURRENT_TOKEN)
  }
}
