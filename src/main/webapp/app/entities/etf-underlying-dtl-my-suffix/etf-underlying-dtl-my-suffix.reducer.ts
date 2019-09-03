import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfUnderlyingDtlMySuffix, defaultValue } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFUNDERLYINGDTL_LIST: 'etfUnderlyingDtl/FETCH_ETFUNDERLYINGDTL_LIST',
  FETCH_ETFUNDERLYINGDTL: 'etfUnderlyingDtl/FETCH_ETFUNDERLYINGDTL',
  CREATE_ETFUNDERLYINGDTL: 'etfUnderlyingDtl/CREATE_ETFUNDERLYINGDTL',
  UPDATE_ETFUNDERLYINGDTL: 'etfUnderlyingDtl/UPDATE_ETFUNDERLYINGDTL',
  DELETE_ETFUNDERLYINGDTL: 'etfUnderlyingDtl/DELETE_ETFUNDERLYINGDTL',
  RESET: 'etfUnderlyingDtl/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfUnderlyingDtlMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfUnderlyingDtlMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfUnderlyingDtlMySuffixState = initialState, action): EtfUnderlyingDtlMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFUNDERLYINGDTL):
    case REQUEST(ACTION_TYPES.UPDATE_ETFUNDERLYINGDTL):
    case REQUEST(ACTION_TYPES.DELETE_ETFUNDERLYINGDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL):
    case FAILURE(ACTION_TYPES.CREATE_ETFUNDERLYINGDTL):
    case FAILURE(ACTION_TYPES.UPDATE_ETFUNDERLYINGDTL):
    case FAILURE(ACTION_TYPES.DELETE_ETFUNDERLYINGDTL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFUNDERLYINGDTL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFUNDERLYINGDTL):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFUNDERLYINGDTL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFUNDERLYINGDTL):
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

const apiUrl = 'api/etf-underlying-dtls';

// Actions

export const getEntities: ICrudGetAllAction<IEtfUnderlyingDtlMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFUNDERLYINGDTL_LIST,
  payload: axios.get<IEtfUnderlyingDtlMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfUnderlyingDtlMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFUNDERLYINGDTL,
    payload: axios.get<IEtfUnderlyingDtlMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfUnderlyingDtlMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFUNDERLYINGDTL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfUnderlyingDtlMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFUNDERLYINGDTL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfUnderlyingDtlMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFUNDERLYINGDTL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
