import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';

export interface IMiMySuffix {
  id?: number;
  miCode?: string;
  miName?: string;
  etfProducts?: IEtfProductMySuffix[];
}

export const defaultValue: Readonly<IMiMySuffix> = {};
