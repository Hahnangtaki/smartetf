import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfProductDealerMySuffix, defaultValue } from 'app/shared/model/etf-product-dealer-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFPRODUCTDEALER_LIST: 'etfProductDealer/FETCH_ETFPRODUCTDEALER_LIST',
  FETCH_ETFPRODUCTDEALER: 'etfProductDealer/FETCH_ETFPRODUCTDEALER',
  CREATE_ETFPRODUCTDEALER: 'etfProductDealer/CREATE_ETFPRODUCTDEALER',
  UPDATE_ETFPRODUCTDEALER: 'etfProductDealer/UPDATE_ETFPRODUCTDEALER',
  DELETE_ETFPRODUCTDEALER: 'etfProductDealer/DELETE_ETFPRODUCTDEALER',
  RESET: 'etfProductDealer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfProductDealerMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfProductDealerMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfProductDealerMySuffixState = initialState, action): EtfProductDealerMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFPRODUCTDEALER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFPRODUCTDEALER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFPRODUCTDEALER):
    case REQUEST(ACTION_TYPES.UPDATE_ETFPRODUCTDEALER):
    case REQUEST(ACTION_TYPES.DELETE_ETFPRODUCTDEALER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFPRODUCTDEALER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFPRODUCTDEALER):
    case FAILURE(ACTION_TYPES.CREATE_ETFPRODUCTDEALER):
    case FAILURE(ACTION_TYPES.UPDATE_ETFPRODUCTDEALER):
    case FAILURE(ACTION_TYPES.DELETE_ETFPRODUCTDEALER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFPRODUCTDEALER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFPRODUCTDEALER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFPRODUCTDEALER):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFPRODUCTDEALER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFPRODUCTDEALER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/etf-product-dealers';

// Actions

export const getEntities: ICrudGetAllAction<IEtfProductDealerMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFPRODUCTDEALER_LIST,
  payload: axios.get<IEtfProductDealerMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfProductDealerMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFPRODUCTDEALER,
    payload: axios.get<IEtfProductDealerMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfProductDealerMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFPRODUCTDEALER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfProductDealerMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFPRODUCTDEALER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfProductDealerMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFPRODUCTDEALER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
