import {MedClass} from './model-enums/med-class';
import {MedType} from './model-enums/med-type';
import {Elements} from './elements';

export class Medicine {
  createdAt: Date;
  updatedAt: Date;
  id: number;
  medType: MedType;
  medClass: MedClass;
  name: string;
  amount: number;
  price: number;
  soldCount: number;
  elements: Elements[];
}
