import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfExecutionDtlMySuffix, defaultValue } from 'app/shared/model/etf-execution-dtl-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFEXECUTIONDTL_LIST: 'etfExecutionDtl/FETCH_ETFEXECUTIONDTL_LIST',
  FETCH_ETFEXECUTIONDTL: 'etfExecutionDtl/FETCH_ETFEXECUTIONDTL',
  CREATE_ETFEXECUTIONDTL: 'etfExecutionDtl/CREATE_ETFEXECUTIONDTL',
  UPDATE_ETFEXECUTIONDTL: 'etfExecutionDtl/UPDATE_ETFEXECUTIONDTL',
  DELETE_ETFEXECUTIONDTL: 'etfExecutionDtl/DELETE_ETFEXECUTIONDTL',
  RESET: 'etfExecutionDtl/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfExecutionDtlMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfExecutionDtlMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfExecutionDtlMySuffixState = initialState, action): EtfExecutionDtlMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFEXECUTIONDTL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFEXECUTIONDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFEXECUTIONDTL):
    case REQUEST(ACTION_TYPES.UPDATE_ETFEXECUTIONDTL):
    case REQUEST(ACTION_TYPES.DELETE_ETFEXECUTIONDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFEXECUTIONDTL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFEXECUTIONDTL):
    case FAILURE(ACTION_TYPES.CREATE_ETFEXECUTIONDTL):
    case FAILURE(ACTION_TYPES.UPDATE_ETFEXECUTIONDTL):
    case FAILURE(ACTION_TYPES.DELETE_ETFEXECUTIONDTL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFEXECUTIONDTL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFEXECUTIONDTL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFEXECUTIONDTL):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFEXECUTIONDTL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFEXECUTIONDTL):
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

const apiUrl = 'api/etf-execution-dtls';

// Actions

export const getEntities: ICrudGetAllAction<IEtfExecutionDtlMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFEXECUTIONDTL_LIST,
  payload: axios.get<IEtfExecutionDtlMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfExecutionDtlMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFEXECUTIONDTL,
    payload: axios.get<IEtfExecutionDtlMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfExecutionDtlMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFEXECUTIONDTL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfExecutionDtlMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFEXECUTIONDTL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfExecutionDtlMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFEXECUTIONDTL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
