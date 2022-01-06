import { Component, OnInit } from '@angular/core';
import { Product } from './Product';
import { UserServiceService } from './user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userinfo',
  templateUrl: './userinfo.component.html',
  styleUrls: ['./userinfo.component.css']
})
export class UserinfoComponent implements OnInit {
  
  public products: Product[];
  user_name: string;
  log_inout: string;
  avg: number;
  sname :string;

  constructor(public service: UserServiceService, private router: Router) { }

  ngOnInit(): void {
    this.home();
  }

  home() {
    this.service.getAllShoes().subscribe(prod => {
      this.products = prod;
    });
  }

  toReview(product: Product) {
    this.service.setProductId(product.prodId);
    this.router.navigate(['/users/reviews']);
    console.log("Product detailss....");
  }

  onSearch() {
    this.service.getSearchedProducts(this.sname).subscribe(
      data => {
        console.log("response received");
        this.products = data;
        this.service.setSearchedProducts(this.products);
        console.log(this.products);
        },
      error => {
        console.log(error);
      })
  }

  logout() {
    sessionStorage.removeItem("Authorization");
    this.router.navigate(['users']);
  }

  login() {
    this.router.navigate(['/users/login']);
  }
}
