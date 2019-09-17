import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDealerParticipantMySuffix, defaultValue } from 'app/shared/model/dealer-participant-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_DEALERPARTICIPANT_LIST: 'dealerParticipant/FETCH_DEALERPARTICIPANT_LIST',
  FETCH_DEALERPARTICIPANT: 'dealerParticipant/FETCH_DEALERPARTICIPANT',
  CREATE_DEALERPARTICIPANT: 'dealerParticipant/CREATE_DEALERPARTICIPANT',
  UPDATE_DEALERPARTICIPANT: 'dealerParticipant/UPDATE_DEALERPARTICIPANT',
  DELETE_DEALERPARTICIPANT: 'dealerParticipant/DELETE_DEALERPARTICIPANT',
  RESET: 'dealerParticipant/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDealerParticipantMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DealerParticipantMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: DealerParticipantMySuffixState = initialState, action): DealerParticipantMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEALERPARTICIPANT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEALERPARTICIPANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DEALERPARTICIPANT):
    case REQUEST(ACTION_TYPES.UPDATE_DEALERPARTICIPANT):
    case REQUEST(ACTION_TYPES.DELETE_DEALERPARTICIPANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DEALERPARTICIPANT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEALERPARTICIPANT):
    case FAILURE(ACTION_TYPES.CREATE_DEALERPARTICIPANT):
    case FAILURE(ACTION_TYPES.UPDATE_DEALERPARTICIPANT):
    case FAILURE(ACTION_TYPES.DELETE_DEALERPARTICIPANT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALERPARTICIPANT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALERPARTICIPANT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEALERPARTICIPANT):
    case SUCCESS(ACTION_TYPES.UPDATE_DEALERPARTICIPANT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEALERPARTICIPANT):
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

const apiUrl = 'api/dealer-participants';

// Actions

export const getEntities: ICrudGetAllAction<IDealerParticipantMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEALERPARTICIPANT_LIST,
  payload: axios.get<IDealerParticipantMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDealerParticipantMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEALERPARTICIPANT,
    payload: axios.get<IDealerParticipantMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDealerParticipantMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEALERPARTICIPANT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDealerParticipantMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEALERPARTICIPANT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDealerParticipantMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEALERPARTICIPANT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
