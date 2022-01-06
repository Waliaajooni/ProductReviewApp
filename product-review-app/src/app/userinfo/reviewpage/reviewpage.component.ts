import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { __asyncValues } from 'tslib';
import { Product } from '../Product';
import { Review } from '../Review';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-reviewpage',
  templateUrl: './reviewpage.component.html',
  styleUrls: ['./reviewpage.component.css']
})
export class ReviewpageComponent implements OnInit {

  public revs: Review[];
  public prodct: Product;
  public revw = new Review();
  sname: string;
  products: Product[];
  currentRate = 0;
  avgRate : number;
  sum  = 0;
  n = 0;

  constructor(public service: UserServiceService, private router: Router) { }

  ngOnInit(): void {
    this.showProducts();
    console.log(this.prodct);
  }

  homepage() {
    this.router.navigate(['users']);
  }

  onsearch() {
    this.router.navigate(['users']);
  }

  addReviewOfProduct() {
    this.service.addReview(this.revw).subscribe(
      data => {
        console.log("response received");
        this.showProducts();
        this.revw.review = '';
        this.revw.rating = 0;
        },
      error => {
        console.log("Exception occured");
      })
  }
  
  logout() {
    sessionStorage.removeItem("Authorization");
    this.router.navigate(['users']);
  }

  login() {
    this.router.navigate(['/users/login']);
  }

  showProducts() {
    this.service.getAllReviews().subscribe(prod => {
    this.prodct = prod;

    this.revs = this.prodct.reviews;

    for (let orev of this.prodct.reviews) {
      this.n += 1;
      this.sum += orev.rating;
    }

    this.avgRate = ((this.sum)/(this.n));
    this.service.setRatings(this.avgRate);
    });

  }

}