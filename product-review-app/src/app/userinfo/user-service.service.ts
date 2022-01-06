import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Product } from './Product';
import { Review } from './Review';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  pId: number;
  public lusername = 'user';
  public luser: User;
  sproducts: Product[];
  avgStar = 0;
  sname: string;
  np!: Product[];
  //token!: string;
  
  constructor(private _http: HttpClient) { }

  public setProducts(prods: Product[]) {
    this.sproducts = prods;
  }

  public setSearchedProducts(prods: Product[]) {
    this.sproducts = prods;
  }

  public getSearchedProductsFromHeading() {
    return this.sproducts;
  }

  public setProductId(prId: number) {
    this.pId = prId;
    console.log(this.pId);
  }

  public setUser(user: User) {
    this.lusername = user.usrname;
    console.log(this.lusername);
  }

  public getUser() {
    console.log(this.lusername);
    return this.lusername;
  }

  public setRatings(rate: number) {
    this.avgStar = rate;
  }

  public getRatings() {
    return this.avgStar;
  }

  public loginUserFromRemote(user: User):Observable<any> {
    return this._http.post<any>("http://localhost:8080/login", user);
  }

  public registerUserFromRemote(user: User):Observable<any> {
    return this._http.post<any>("http://localhost:8080/register", user);
  }

  public getSearchedProducts(searchedname: string):Observable<Product[]> {
    let token = sessionStorage.getItem('Authorization');

    if (token != null) {
      const httpHeader = new HttpHeaders({
        Authorization : token,
      });

      return this._http.post<Product[]>("http://localhost:8080/products/search", searchedname, {headers :httpHeader});
    }

    return this._http.post<Product[]>("http://localhost:8080/products/search", searchedname);
  }

  public getAllUsers() {
    return this._http.get<User[]>("http://localhost:8080/usernames");
  }

  public getAllShoes():Observable<Product[]> {

    let token = sessionStorage.getItem('Authorization');

    if (token != null) {
      const httpHeader = new HttpHeaders({
        Authorization : token,
      });

      return this._http.get<Product[]>("http://localhost:8080/users", {headers :httpHeader});
    }
    
    return this._http.get<Product[]>("http://localhost:8080/users");
    
  }

  public getAllReviews():Observable<Product> {
    console.log(this.pId);

    let token = sessionStorage.getItem('Authorization');

    if (token != null) {
      const httpHeader = new HttpHeaders({
        Authorization : token,
      });

      return this._http.get<Product>("http://localhost:8080/users/" + this.pId, {headers :httpHeader});
    }

    return this._http.get<Product>("http://localhost:8080/users/" + this.pId);
  }

  public isLoggedIn() {
    let lusr = sessionStorage.getItem("Authorization");
    console.log(!(lusr === null));
    return !(lusr === null);
  }

  public addReview(rev: Review):Observable<any> {
    console.log(this.pId);
    console.log(rev.reviewId);

    let token = sessionStorage.getItem('Authorization');

    if (token != null) {
      const httpHeader = new HttpHeaders({
        Authorization : token,
      });

      return this._http.post<any>("http://localhost:8080/products/" + this.pId, rev, {headers :httpHeader});
    }
    
    return this._http.post<any>("http://localhost:8080/products/" + this.pId, rev);
  }
}
