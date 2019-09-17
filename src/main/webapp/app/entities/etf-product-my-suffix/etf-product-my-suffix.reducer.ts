import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfProductMySuffix, defaultValue } from 'app/shared/model/etf-product-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFPRODUCT_LIST: 'etfProduct/FETCH_ETFPRODUCT_LIST',
  FETCH_ETFPRODUCT: 'etfProduct/FETCH_ETFPRODUCT',
  CREATE_ETFPRODUCT: 'etfProduct/CREATE_ETFPRODUCT',
  UPDATE_ETFPRODUCT: 'etfProduct/UPDATE_ETFPRODUCT',
  DELETE_ETFPRODUCT: 'etfProduct/DELETE_ETFPRODUCT',
  RESET: 'etfProduct/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfProductMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfProductMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfProductMySuffixState = initialState, action): EtfProductMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFPRODUCT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFPRODUCT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFPRODUCT):
    case REQUEST(ACTION_TYPES.UPDATE_ETFPRODUCT):
    case REQUEST(ACTION_TYPES.DELETE_ETFPRODUCT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFPRODUCT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFPRODUCT):
    case FAILURE(ACTION_TYPES.CREATE_ETFPRODUCT):
    case FAILURE(ACTION_TYPES.UPDATE_ETFPRODUCT):
    case FAILURE(ACTION_TYPES.DELETE_ETFPRODUCT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFPRODUCT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFPRODUCT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFPRODUCT):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFPRODUCT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFPRODUCT):
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

const apiUrl = 'api/etf-products';

// Actions

export const getEntities: ICrudGetAllAction<IEtfProductMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFPRODUCT_LIST,
  payload: axios.get<IEtfProductMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfProductMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFPRODUCT,
    payload: axios.get<IEtfProductMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfProductMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFPRODUCT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfProductMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFPRODUCT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfProductMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFPRODUCT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
