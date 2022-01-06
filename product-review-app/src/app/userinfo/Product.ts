import { Byte } from "@angular/compiler/src/util";
import { Review } from "./Review";

export class Product {
    prodId: number;
    prodName: string;
    imageUrl: string;
    prodModel: string;
	prodBrand: string;
    prodStorage: string;
    
    reviews: Review[];
    
    constructor() {}
}