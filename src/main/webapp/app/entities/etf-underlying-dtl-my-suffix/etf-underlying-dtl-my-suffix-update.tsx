import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtfUnderlyingMySuffix } from 'app/shared/model/etf-underlying-my-suffix.model';
import { getEntities as getEtfUnderlyings } from 'app/entities/etf-underlying-my-suffix/etf-underlying-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './etf-underlying-dtl-my-suffix.reducer';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfUnderlyingDtlMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfUnderlyingDtlMySuffixUpdateState {
  isNew: boolean;
  etfUnderlyingId: string;
}

export class EtfUnderlyingDtlMySuffixUpdate extends React.Component<
  IEtfUnderlyingDtlMySuffixUpdateProps,
  IEtfUnderlyingDtlMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      etfUnderlyingId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEtfUnderlyings();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfUnderlyingDtlEntity } = this.props;
      const entity = {
        ...etfUnderlyingDtlEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-underlying-dtl-my-suffix');
  };

  render() {
    const { etfUnderlyingDtlEntity, etfUnderlyings, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfUnderlyingDtl.home.createOrEditLabel">Create or edit a EtfUnderlyingDtl</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfUnderlyingDtlEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-underlying-dtl-my-suffix-id">ID</Label>
                    <AvInput id="etf-underlying-dtl-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="stockCodeLabel" for="etf-underlying-dtl-my-suffix-stockCode">
                    Stock Code
                  </Label>
                  <AvField
                    id="etf-underlying-dtl-my-suffix-stockCode"
                    type="text"
                    name="stockCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="etf-underlying-dtl-my-suffix-weight">
                    Weight
                  </Label>
                  <AvField id="etf-underlying-dtl-my-suffix-weight" type="string" className="form-control" name="weight" />
                </AvGroup>
                <AvGroup>
                  <Label for="etf-underlying-dtl-my-suffix-etfUnderlying">Etf Underlying</Label>
                  <AvInput id="etf-underlying-dtl-my-suffix-etfUnderlying" type="select" className="form-control" name="etfUnderlyingId">
                    <option value="" key="0" />
                    {etfUnderlyings
                      ? etfUnderlyings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-underlying-dtl-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfUnderlyings: storeState.etfUnderlying.entities,
  etfUnderlyingDtlEntity: storeState.etfUnderlyingDtl.entity,
  loading: storeState.etfUnderlyingDtl.loading,
  updating: storeState.etfUnderlyingDtl.updating,
  updateSuccess: storeState.etfUnderlyingDtl.updateSuccess
});

const mapDispatchToProps = {
  getEtfUnderlyings,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfUnderlyingDtlMySuffixUpdate);
