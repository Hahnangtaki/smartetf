import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfHistoryMySuffix, defaultValue } from 'app/shared/model/etf-history-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFHISTORY_LIST: 'etfHistory/FETCH_ETFHISTORY_LIST',
  FETCH_ETFHISTORY: 'etfHistory/FETCH_ETFHISTORY',
  CREATE_ETFHISTORY: 'etfHistory/CREATE_ETFHISTORY',
  UPDATE_ETFHISTORY: 'etfHistory/UPDATE_ETFHISTORY',
  DELETE_ETFHISTORY: 'etfHistory/DELETE_ETFHISTORY',
  RESET: 'etfHistory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfHistoryMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfHistoryMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfHistoryMySuffixState = initialState, action): EtfHistoryMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFHISTORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFHISTORY):
    case REQUEST(ACTION_TYPES.UPDATE_ETFHISTORY):
    case REQUEST(ACTION_TYPES.DELETE_ETFHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFHISTORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFHISTORY):
    case FAILURE(ACTION_TYPES.CREATE_ETFHISTORY):
    case FAILURE(ACTION_TYPES.UPDATE_ETFHISTORY):
    case FAILURE(ACTION_TYPES.DELETE_ETFHISTORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFHISTORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFHISTORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFHISTORY):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFHISTORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFHISTORY):
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

const apiUrl = 'api/etf-histories';

// Actions

export const getEntities: ICrudGetAllAction<IEtfHistoryMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFHISTORY_LIST,
  payload: axios.get<IEtfHistoryMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfHistoryMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFHISTORY,
    payload: axios.get<IEtfHistoryMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfHistoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFHISTORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfHistoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFHISTORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfHistoryMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFHISTORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
