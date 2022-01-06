import { Product } from './Product';
import { User } from './User';

export class Review {
    reviewId: number;
    rating: number;
    review: string;
    product: Product;
    user: User;

    constructor() {}
}