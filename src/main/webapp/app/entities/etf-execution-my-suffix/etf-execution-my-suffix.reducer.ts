import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfExecutionMySuffix, defaultValue } from 'app/shared/model/etf-execution-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFEXECUTION_LIST: 'etfExecution/FETCH_ETFEXECUTION_LIST',
  FETCH_ETFEXECUTION: 'etfExecution/FETCH_ETFEXECUTION',
  CREATE_ETFEXECUTION: 'etfExecution/CREATE_ETFEXECUTION',
  UPDATE_ETFEXECUTION: 'etfExecution/UPDATE_ETFEXECUTION',
  DELETE_ETFEXECUTION: 'etfExecution/DELETE_ETFEXECUTION',
  RESET: 'etfExecution/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfExecutionMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfExecutionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfExecutionMySuffixState = initialState, action): EtfExecutionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFEXECUTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFEXECUTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFEXECUTION):
    case REQUEST(ACTION_TYPES.UPDATE_ETFEXECUTION):
    case REQUEST(ACTION_TYPES.DELETE_ETFEXECUTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFEXECUTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFEXECUTION):
    case FAILURE(ACTION_TYPES.CREATE_ETFEXECUTION):
    case FAILURE(ACTION_TYPES.UPDATE_ETFEXECUTION):
    case FAILURE(ACTION_TYPES.DELETE_ETFEXECUTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFEXECUTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFEXECUTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFEXECUTION):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFEXECUTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFEXECUTION):
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

const apiUrl = 'api/etf-executions';

// Actions

export const getEntities: ICrudGetAllAction<IEtfExecutionMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFEXECUTION_LIST,
  payload: axios.get<IEtfExecutionMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfExecutionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFEXECUTION,
    payload: axios.get<IEtfExecutionMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfExecutionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFEXECUTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfExecutionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFEXECUTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfExecutionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFEXECUTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
